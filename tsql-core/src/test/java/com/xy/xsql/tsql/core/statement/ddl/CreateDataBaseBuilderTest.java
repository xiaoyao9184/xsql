package com.xy.xsql.tsql.core.statement.ddl;

import com.xy.xsql.tsql.model.statement.ddl.create.CreateDataBase;
import org.junit.Assert;
import org.junit.Test;

import static com.xy.xsql.tsql.core.statement.ddl.CreateDataBaseBuilder.CREATE_DATABASE;
import static org.junit.Assert.*;

/**
 * Created by xiaoyao9184 on 2017/8/4.
 */
public class CreateDataBaseBuilderTest {


    /*
    Examples
    See https://docs.microsoft.com/en-us/sql/t-sql/statements/create-database-sql-server-transact-sql#examples
     */

    // @formatter:off
    /**
     * CREATE DATABASE mytest
     */
    public CreateDataBase exampleA = CREATE_DATABASE("mytest");
    // @formatter:on

    @Test
    public void testExampleA(){
        Assert.assertEquals(exampleA.getDatabaseName(),"mytest");
    }

    // @formatter:off
    /**
     * CREATE DATABASE Sales
    ON
    ( NAME = Sales_dat,
        FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\saledat.mdf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 5 )
    LOG ON
    ( NAME = Sales_log,
        FILENAME = 'C:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\DATA\salelog.ldf',
        SIZE = 5MB,
        MAXSIZE = 25MB,
        FILEGROWTH = 5MB )
     */
    public CreateDataBase exampleB = new CreateDataBaseBuilder()
            .$("Sales")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleB(){
        Assert.assertEquals(exampleB.getDatabaseName(),"Sales");
    }

    // @formatter:off
    /**
     * CREATE DATABASE Archive
    ON
    PRIMARY
        (NAME = Arch1,
        FILENAME = 'D:\SalesData\archdat1.mdf',
        SIZE = 100MB,
        MAXSIZE = 200,
        FILEGROWTH = 20),
        ( NAME = Arch2,
        FILENAME = 'D:\SalesData\archdat2.ndf',
        SIZE = 100MB,
        MAXSIZE = 200,
        FILEGROWTH = 20),
        ( NAME = Arch3,
        FILENAME = 'D:\SalesData\archdat3.ndf',
        SIZE = 100MB,
        MAXSIZE = 200,
        FILEGROWTH = 20)
    LOG ON
       (NAME = Archlog1,
        FILENAME = 'D:\SalesData\archlog1.ldf',
        SIZE = 100MB,
        MAXSIZE = 200,
        FILEGROWTH = 20),
       (NAME = Archlog2,
        FILENAME = 'D:\SalesData\archlog2.ldf',
        SIZE = 100MB,
        MAXSIZE = 200,
        FILEGROWTH = 20)
     */
    public CreateDataBase exampleC = new CreateDataBaseBuilder()
            .$("Archive")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleC(){
        Assert.assertEquals(exampleC.getDatabaseName(),"Archive");
    }

    // @formatter:off
    /**
     * CREATE DATABASE Sales
    ON PRIMARY
    ( NAME = SPri1_dat,
        FILENAME = 'D:\SalesData\SPri1dat.mdf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 15% ),
    ( NAME = SPri2_dat,
        FILENAME = 'D:\SalesData\SPri2dt.ndf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 15% ),
    FILEGROUP SalesGroup1
    ( NAME = SGrp1Fi1_dat,
        FILENAME = 'D:\SalesData\SG1Fi1dt.ndf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 5 ),
    ( NAME = SGrp1Fi2_dat,
        FILENAME = 'D:\SalesData\SG1Fi2dt.ndf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 5 ),
    FILEGROUP SalesGroup2
    ( NAME = SGrp2Fi1_dat,
        FILENAME = 'D:\SalesData\SG2Fi1dt.ndf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 5 ),
    ( NAME = SGrp2Fi2_dat,
        FILENAME = 'D:\SalesData\SG2Fi2dt.ndf',
        SIZE = 10,
        MAXSIZE = 50,
        FILEGROWTH = 5 )
    LOG ON
    ( NAME = Sales_log,
        FILENAME = 'E:\SalesLog\salelog.ldf',
        SIZE = 5MB,
        MAXSIZE = 25MB,
        FILEGROWTH = 5MB )
     */
    public CreateDataBase exampleD = new CreateDataBaseBuilder()
            .$("Sales")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleD(){
        Assert.assertEquals(exampleD.getDatabaseName(),"Sales");
    }

    // @formatter:off
    /**
     * CREATE DATABASE Archive
      ON (FILENAME = 'D:\SalesData\archdat1.mdf')
      FOR ATTACH
     */
    public CreateDataBase exampleE = new CreateDataBaseBuilder()
            .$("Archive")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleE(){
        Assert.assertEquals(exampleE.getDatabaseName(),"Archive");
    }

    // @formatter:off
    /**
     * CREATE DATABASE sales_snapshot0600 ON
        ( NAME = SPri1_dat, FILENAME = 'D:\SalesData\SPri1dat_0600.ss'),
        ( NAME = SPri2_dat, FILENAME = 'D:\SalesData\SPri2dt_0600.ss'),
        ( NAME = SGrp1Fi1_dat, FILENAME = 'D:\SalesData\SG1Fi1dt_0600.ss'),
        ( NAME = SGrp1Fi2_dat, FILENAME = 'D:\SalesData\SG1Fi2dt_0600.ss'),
        ( NAME = SGrp2Fi1_dat, FILENAME = 'D:\SalesData\SG2Fi1dt_0600.ss'),
        ( NAME = SGrp2Fi2_dat, FILENAME = 'D:\SalesData\SG2Fi2dt_0600.ss')
    AS SNAPSHOT OF Sales
     */
    public CreateDataBase exampleF = new CreateDataBaseBuilder()
            .$("sales_snapshot0600")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleF(){
        Assert.assertEquals(exampleF.getDatabaseName(),"sales_snapshot0600");
    }

    // @formatter:off
    /**
     * CREATE DATABASE MyOptionsTest
    COLLATE French_CI_AI
    WITH TRUSTWORTHY ON, DB_CHAINING ON
     */
    public CreateDataBase exampleG = new CreateDataBaseBuilder()
            .$("MyOptionsTest")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleG(){
        Assert.assertEquals(exampleG.getDatabaseName(),"MyOptionsTest");
    }

    // @formatter:off
    /**
     * CREATE DATABASE AdventureWorks2012 ON
        (FILENAME = 'c:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\Data\AdventureWorks2012_data.mdf'),
        (FILENAME = 'c:\Program Files\Microsoft SQL Server\MSSQL13.MSSQLSERVER\MSSQL\Data\AdventureWorks2012_log.ldf'),
        (FILENAME = 'c:\myFTCatalogs\AdvWksFtCat')
    FOR ATTACH
     */
    public CreateDataBase exampleH = new CreateDataBaseBuilder()
            .$("AdventureWorks2012")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleH(){
        Assert.assertEquals(exampleH.getDatabaseName(),"AdventureWorks2012");
    }

    // @formatter:off
    /**
     * CREATE DATABASE FileStreamDB
    ON PRIMARY
        (
        NAME = FileStreamDB_data
        ,FILENAME = ''' + @data_path + 'FileStreamDB_data.mdf''
        ,SIZE = 10MB
        ,MAXSIZE = 50MB
        ,FILEGROWTH = 15%
        ),
    FILEGROUP FileStreamPhotos CONTAINS FILESTREAM DEFAULT
        (
        NAME = FSPhotos
        ,FILENAME = ''C:\MyFSfolder\Photos''
    -- SIZE and FILEGROWTH should not be specified here.
    -- If they are specified an error will be raised.
    , MAXSIZE = 5000 MB
        ),
        (
          NAME = FSPhotos2
          , FILENAME = ''D:\MyFSfolder\Photos''
          , MAXSIZE = 10000 MB
         ),
    FILEGROUP FileStreamResumes CONTAINS FILESTREAM
        (
        NAME = FileStreamResumes
        ,FILENAME = ''C:\MyFSfolder\Resumes''
        )
    LOG ON
        (
        NAME = FileStream_log
        ,FILENAME = ''' + @data_path + 'FileStreamDB_log.ldf''
        ,SIZE = 5MB
        ,MAXSIZE = 25MB
        ,FILEGROWTH = 5MB
        )
     */
    public CreateDataBase exampleI = new CreateDataBaseBuilder()
            .$("FileStreamDB")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleI(){
        Assert.assertEquals(exampleI.getDatabaseName(),"FileStreamDB");
    }

    // @formatter:off
    /**
     * CREATE DATABASE [BlobStore1]
    CONTAINMENT = NONE
    ON PRIMARY
    (
        NAME = N'BlobStore1',
        FILENAME = N'C:\BlobStore\BlobStore1.mdf',
        SIZE = 100MB,
        MAXSIZE = UNLIMITED,
        FILEGROWTH = 1MB
    ),
    FILEGROUP [FS] CONTAINS FILESTREAM DEFAULT
    (
        NAME = N'FS1',
        FILENAME = N'C:\BlobStore\FS1',
        MAXSIZE = UNLIMITED
    ),
    (
        NAME = N'FS2',
        FILENAME = N'C:\BlobStore\FS2',
        MAXSIZE = 100MB
    )
    LOG ON
    (
        NAME = N'BlobStore1_log',
        FILENAME = N'C:\BlobStore\BlobStore1_log.ldf',
        SIZE = 100MB,
        MAXSIZE = 1GB,
        FILEGROWTH = 1MB
    )
     */
    public CreateDataBase exampleJ = new CreateDataBaseBuilder()
            .$("[BlobStore1]")
            //TODO
//            .$On()
            .build();
    // @formatter:on

    @Test
    public void testExampleJ(){
        Assert.assertEquals(exampleJ.getDatabaseName(),"[BlobStore1]");
    }


}