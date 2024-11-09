package org.seoultech.tableapi.menu.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.seoultech.tableapi.menu.domain.Menu;
import org.seoultech.tableapi.domain.menu.domain.QMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRepositoryImpl implements MenuRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Menu> findAllByRestaurantId(Long restaurantId) {
        QMenu menu = QMenu.menu;
        return queryFactory
                .selectFrom(menu)
                .where(menu.restaurant.id.eq(restaurantId)
                        .and(menu.isAvailable.isTrue()))
                .fetch();
    }

    @Override
    public Optional<Menu> findMenuWithRestaurant(Long id) {
        QMenu menu = QMenu.menu;
        Menu serachedMenu = queryFactory
                .selectFrom(menu)
                .leftJoin(menu.restaurant).fetchJoin()
                .where(menu.id.eq(id)
                        .and(menu.isAvailable.isTrue()))
                .fetchOne();
        return Optional.ofNullable(serachedMenu);
    }
}
