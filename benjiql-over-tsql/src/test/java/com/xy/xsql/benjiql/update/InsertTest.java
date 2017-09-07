package com.xy.xsql.benjiql.update;

import com.xy.xsql.benjiql.example.Conspiracy;
import com.xy.xsql.benjiql.example.Person;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.meta.MetaManager;
import com.xy.xsql.entity.model.jpql.PlaceholderJPql;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

import static com.xy.xsql.benjiql.update.Upsert.insert;
import static org.junit.Assert.assertEquals;

public class InsertTest {

    @Before
    public void init(){
        BlockManager.init(MetaManager.INSTANCE);
    }

    Person person = new Person();

    @Test
    public void should_match_example() {
        String sql = insert(person)
            .value(Person::getFirstName)
            .value(Person::getFavouriteNumber)
            .toJPql().getSql();

        String check = "INSERT person (first_name, favourite_number) VALUES ( ?, ? )";
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
        Person smith = new Person("agent","smith");
        Conspiracy nsa = new Conspiracy("nsa");

        String sql = insert(nsa, smith)
                .valueLeft(Conspiracy::getName)
                .valueRight(Person::getLastName)
                .valueRight(Person::getFirstName)
                .toJPql().getSql();

        String check = "INSERT conspiracy_person (conspiracy_name, person_last_name, person_first_name) VALUES ( ?, ?, ? )";
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

        PlaceholderJPql jpql = insert(person)
            .value(Person::getFirstName)
            .value(Person::getFavouriteNumber)
            .toJPql();

        assertEquals(jpql.getArgs().length,2);
    }
}
