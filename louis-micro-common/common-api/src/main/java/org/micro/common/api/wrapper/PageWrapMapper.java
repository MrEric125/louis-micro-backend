

package org.micro.common.api.wrapper;

import org.micro.common.util.PageInfo;
import org.micro.common.util.SortInfo;
import org.springframework.data.domain.Page;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class Page wrap mapper.
 *
 * @author John·Louis
 */
public class PageWrapMapper {

	/**
	 * Instantiates a new page wrap mapper.
	 */
	private PageWrapMapper() {
	}

	private static <E> PageWrapper<E> wrap(int code, String message, E o, PageInfo Pageable) {
		return new PageWrapper<>(code, message, o, Pageable);
	}

	/**
	 * Wrap data with default code=200.
	 *
	 * @param <E>      the type parameter
	 * @param o        the o
	 * @param Pageable the page util
	 *
	 * @return the page wrapper
	 */
	public static <E> PageWrapper wrap(E o, PageInfo Pageable) {
		return wrap(WrapperMassage.SUCCESS_CODE, WrapperMassage.SUCCESS_MESSAGE, o, Pageable);
	}

	/**
	 * 如果是返回的分页信息那么一定是成功的，就没有errorMsg
	 *注意，在查询的时候需要指定Pageable参数，否则显示的页码信息会有错误
	 *
	 * @param <T>
	 * @return todo 这个地方不太好指定泛型类型
	 */
	@SuppressWarnings("unchecked")
	protected <T> Wrapper<T> handlePageAndSortResult(Object t) {
		if (t instanceof Page) {
			Page page = (Page) t;
			List<SortInfo> sortInfos = new ArrayList<>();
			if (page.getSort().isSorted()) {
				sortInfos = page.getSort().get().map(x -> {
					SortInfo sortInfo = new SortInfo();
					sortInfo.setDirection(x.getDirection().name());
					sortInfo.setProperties(x.getProperty());
					return sortInfo;

				}).collect(Collectors.toList());
			}
			return PageWrapMapper.wrap(page.getContent(),
					PageInfo.builder()
							.currentPage(page.getNumber())
							.pageSize(page.getSize())
							.totalPage(page.getTotalPages())
							.totalElement(page.getTotalElements())
							.sorted(page.getSort().isSorted())
							.sortInfos(sortInfos)
							.build());
		} else {
			return null;
		}
	}
	/**
	 * Un wrapper.
	 *
	 * @param <E>     the type parameter
	 * @param wrapper the wrapper
	 *
	 * @return the e
	 */
	public static <E> E unWrap(PageWrapper<E> wrapper) {
		return wrapper.getResult();
	}
}
