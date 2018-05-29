package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.example.Person;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static com.xy.xsql.benjiql.update.Upsert.update;
import static org.junit.Assert.assertEquals;

public class UpdateTest {

    @Before
    public void init(){
        BlockManager.init(MetaManager.INSTANCE);
    }

    Person person = new Person();

    @Test
    public void should_match_example_with_no_restrictions() {
        String sql = update(person)
            .value(Person::getFirstName)
            .value(Person::getFavouriteNumber)
            .toJSql().getSql();

        String check = "UPDATE person SET first_name = ?, favourite_number = ?";
        check = check
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");

        sql = sql
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");
        assertEquals(
                check,
                sql);
    }

    @Test
    public void should_match_example_with_restrictions() {
        String sql = update(person)
            .value(Person::getFirstName)
            .value(Person::getFavouriteNumber)
            .where(Person::getLastName)
            .equalTo("weber")
            .and(Person::getFirstName)
            .notEqualTo("bob")
            .and(Person::getFirstName)
            .like("b%")
            .toJSql().getSql();

        String check = "UPDATE person SET first_name = ?, favourite_number = ? WHERE last_name = ? AND first_name != ? AND first_name LIKE ?";
        check = check
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");

        sql = sql
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");
        assertEquals(
                check,
                sql);
    }

    @Test
    public void should_set_values() throws SQLException {
        person.setFirstName("asdf");
        person.setFavouriteNumber(55);

        PlaceholderJSql jpql = update(person)
            .value(Person::getFirstName)
            .value(Person::getFavouriteNumber)
            .where(Person::getLastName)
            .equalTo("weber")
            .and(Person::getFavouriteNumber)
            .equalTo(6)
            .toJSql();

        assertEquals(jpql.getArgs().length,4);

        String sql = update(person)
                .value(Person::getFirstName)
                .value(Person::getFavouriteNumber)
                .where(Person::getLastName)
                .equalTo("weber")
                .and(Person::getFavouriteNumber)
                .equalTo(6)
                .toSql();

        String check = "UPDATE person SET first_name = 'asdf' , favourite_number = 55 WHERE last_name = 'weber' AND favourite_number = 6";
        check = check
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");

        sql = sql
                .replaceAll(" ", "")
                .replaceAll("\t", "")
                .replaceAll("\n", "");
        assertEquals(
                check,
                sql);
    }
}
