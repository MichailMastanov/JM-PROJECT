package jm.stockx.api.dao;

import jm.stockx.dto.user.UserDto;
import jm.stockx.dto.user.UserPutDto;
import jm.stockx.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDAO<User, Long> implements UserDAO {

    @Override
    public UserDto getUserDtoByUserUsername(String username) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.username =: username", UserDto.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByUserEmail(String email) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.email =: email", UserDto.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByUserAppleId(String appleId) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.appleUserId =: appleId", UserDto.class)
                .setParameter("appleId", appleId)
                .getSingleResult();
    }

    @Override
    public UserDto getUserDtoByUserId(Long id) {
        return entityManager.createQuery("" +
                "SELECT NEW jm.stockx.dto.user.UserDto(" +
                "u.firstName," +
                "u.lastName," +
                "u.email," +
                "u.username," +
                "u.password," +
                "u.sellerLevel," +
                "u.vacationMode," +
                "u.localeTag" +
                ")" +
                "FROM User AS u " +
                "WHERE u.id =: id", UserDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByUsername(String username) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.username =: username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.createQuery("" +
                "FROM User AS u WHERE u.id =: id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            return entityManager.createQuery("" +
                "FROM User AS u WHERE u.email =: email", User.class)
                .setParameter("email", email)
                .getSingleResult();
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void updateUserFromDto(UserPutDto userPutDto){
        entityManager.createQuery("" +
                "UPDATE User user SET" +
                " user.firstName = :firstNameFromDto," +
                " user.lastName = :lastNameFromDto ")
                .setParameter("firstNameFromDto",userPutDto.getFirstName())
                .setParameter("lastNameFromDto",userPutDto.getLastName())
                .executeUpdate();
    }
}
