package com.security.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.louis.common.api.wrapper.WrapMapper;
import com.louis.core.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 认证成功处理器.
 *
 * @author John·Louis
 */
@Component("pcAuthenticationSuccessHandler")
@Slf4j
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Resource
	private ObjectMapper objectMapper;
	@Resource
	private ClientDetailsService clientDetailsService;

	@Resource
	private AuthorizationServerTokenServices authorizationServerTokenServices;

	private static final String BEARER_TOKEN_TYPE = "Bearer ";

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

		logger.info("登录成功");

		String header = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
			throw new UnapprovedClientAuthenticationException("请求头中无client信息");
		}

		String[] tokens = RequestUtil.extractAndDecodeHeader(header);
		assert tokens.length == 2;

		String clientId = tokens[0];
		String clientSecret = tokens[1];

		ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

		if (clientDetails == null) {
			throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
		} else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
			throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
		}

		TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");

		OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);

		OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

		if (authentication.getPrincipal() instanceof UserDetails) {

			UserDetails principal = (UserDetails) authentication.getPrincipal();
			log.info("用户【 {} 】记录登录日志", principal.getUsername());
		}
		log.info(" 获取token>>>>>>");




		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write((objectMapper.writeValueAsString(WrapMapper.success(token))));

	}

}
