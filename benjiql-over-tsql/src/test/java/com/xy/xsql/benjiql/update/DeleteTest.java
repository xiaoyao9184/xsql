package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.example.Conspiracy;
import com.xy.xsql.benjiql.example.Person;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.model.sql.PlaceholderJSql;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static com.xy.xsql.benjiql.ddl.JoinTables.relationship;
import static com.xy.xsql.benjiql.update.Delete.delete;
import static org.junit.Assert.assertEquals;

public class DeleteTest {

    @Before
    public void init(){
        BlockManager.init(MetaManager.INSTANCE);
    }

    @Test
    public void should_match_example_with_no_restrictions() {
        String sql = delete(Person.class).toSql();

        String check = "DELETE person";

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
        String sql = delete(Person.class)
            .where(Person::getLastName)
            .equalTo("weber")
            .and(Person::getFavouriteNumber)
            .equalTo(6)
            .and(Person::getFirstName)
            .like("%w%")
            .and(Person::getFirstName)
            .notEqualTo("bob")
            .toJSql().getSql();

        String check = "DELETE person WHERE last_name = ? AND favourite_number = ? AND first_name LIKE ? AND first_name != ?";

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
    public void join_example() {
        String sql = delete(relationship(Conspiracy.class, Person.class))
                .whereLeft(Conspiracy::getName)
                .equalTo("nsa")
                .andRight(Person::getLastName)
                .equalTo("smith")
                .toJSql().getSql();

        String check = "DELETE conspiracy_person " +
                "WHERE conspiracy_name = ? " +
                "AND person_last_name = ?";

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
        PlaceholderJSql jpql = delete(Person.class)
                .where(Person::getLastName)
                .equalTo("weber")
                .and(Person::getFavouriteNumber)
                .equalTo(6)
                .toJSql();

        String sql = jpql.getSql();
        String check = "DELETE person WHERE last_name = ? AND favourite_number = ?";
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
        assertEquals(jpql.getArgs().length,2);
    }
}
