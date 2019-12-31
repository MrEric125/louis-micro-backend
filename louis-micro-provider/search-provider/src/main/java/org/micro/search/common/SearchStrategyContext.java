package org.micro.search.common;

/**
 * @author louis
 * <p>
 * Date: 2019/12/30
 * Description:
 * 查询使用的策略模式
 */
public class SearchStrategyContext<T extends CommonSearchResponse,R extends CommonSearchRequest> {

    private SearchStrategy<T,R> strategy;

    public SearchStrategyContext(SearchStrategy<T,R> strategy) {
        this.strategy = strategy;
    }

    public void changeStrategy(SearchStrategy<T,R> strategy) {
        this.strategy = strategy;
    }
    public T executeStrategy(R request) {
        return strategy.execute(request);
    }

}
