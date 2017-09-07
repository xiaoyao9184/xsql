package com.xy.xsql.benjiql.create;

import com.xy.xsql.benjiql.example.Conspiracy;
import com.xy.xsql.benjiql.example.Person;
import com.xy.xsql.block.core.BlockManager;
import com.xy.xsql.block.meta.MetaManager;
import org.junit.Before;
import org.junit.Test;

import static com.xy.xsql.benjiql.create.Create.create;
import static com.xy.xsql.benjiql.ddl.JoinTables.relationship;
import static org.junit.Assert.assertEquals;

public class CreateTest {

    @Before
    public void init(){
        BlockManager.init(MetaManager.INSTANCE);
    }

    static class Address {
        public String getFirstLine() { return null; }
        public Integer getHouseNumber() { return null; }
    }

    @Test
    public void check_create_matches_example() throws Exception {
        String sql = create(Address.class)
            .field(Address::getFirstLine)
            .field(Address::getHouseNumber)
            .toSql();

        assertEquals("CREATE TABLE address ( first_line text, house_number integer )", sql.trim());

    }

    @Test
    public void example_join_table() {
        String sql = create(relationship(Conspiracy.class, Person.class))
            .fieldLeft(Conspiracy::getName)
            .fieldRight(Person::getFirstName)
            .fieldRight(Person::getLastName)
            .toSql();

        assertEquals("CREATE TABLE conspiracy_person ( conspiracy_conspiracy_name text, person_person_first_name text, person_person_last_name text )", sql);
    }
}
