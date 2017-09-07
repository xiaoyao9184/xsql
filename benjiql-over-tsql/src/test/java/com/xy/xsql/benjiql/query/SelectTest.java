package com.xy.xsql.benjiql.query;

import com.xy.xsql.benjiql.example.Conspiracy;
import com.xy.xsql.benjiql.example.Person;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.meta.MetaManager;
import org.junit.Before;
import org.junit.Test;

import static com.xy.xsql.benjiql.ddl.JoinTables.relationship;
import static com.xy.xsql.benjiql.query.Select.from;
import static org.junit.Assert.assertEquals;

/**
 * Created by xiaoyao9184 on 2017/7/27.
 */
public class SelectTest {

    @Before
    public void init(){
        BlockManager.init(MetaManager.INSTANCE);
    }

    @Test
    public void should_match_example() {
        String sql = from(Person.class)
                .where(Person::getFirstName)
                .equalTo("benji")
                .and(Person::getLastName)
                .notEqualTo("foo")
                .and(Person::getLastName)
                .like("web%")
                .and(Person::getFavouriteNumber)
                .equalTo(5)
                .toJPql().getSql();

        String check = "SELECT * FROM person WHERE person.first_name = ? AND person.last_name != ? AND person.last_name LIKE ? AND person.favourite_number = ?";

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
    public void should_allow_joins() {
        String sql = from(Person.class)
                .where(Person::getLastName)
                .equalTo("smith")
                .join(relationship(Conspiracy.class, Person.class).invert())
                .using(Person::getFirstName, Person::getLastName)
                .join(Conspiracy.class)
                .using(Conspiracy::getName)
                .where(Conspiracy::getName)
                .equalTo("nsa")
                .toJPql().getSql();

        String check = "SELECT * FROM person " +
                "JOIN conspiracy_person " +
                "ON person.first_name = conspiracy_person.person_first_name " +
                "AND person.last_name = conspiracy_person.person_last_name " +
                "JOIN conspiracy " +
                "ON conspiracy_person.conspiracy_name = conspiracy.name " +
                "WHERE person.last_name = ? " +
                "AND conspiracy.name = ?";
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