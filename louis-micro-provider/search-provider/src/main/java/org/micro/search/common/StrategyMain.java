package org.micro.search.common;

/**
 * @author louis
 * <p>
 * Date: 2019/12/30
 * Description:
 */
public class StrategyMain {

    public static void main(String[] args) {
        SearchByAllIndex allIndex = new SearchByAllIndex();
        SearchStrategyContext<SearchAllIndexResponse,CommonSearchRequest> allResponse = new SearchStrategyContext<>(allIndex);
        SearchAllIndexResponse execute = allIndex.execute(new CommonSearchRequest());
        System.out.println(execute.getResult());
    }
}
