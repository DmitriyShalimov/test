package com.minexsystems;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CacheMapTest {
    private CacheMap<Integer, String> cache;
    private final static long TIME_TO_LIVE = 1000;


    @Before
    public void setUp() {
        cache=new CacheHashMap<>();
        Clock.setTime( 1000 );
        cache.setTimeToLive( TIME_TO_LIVE );
    }

    @Test
    public void testExpiry()  {
        cache.put( 1, "apple" );
        assertEquals( "apple", cache.get( 1 ) );
        assertFalse( cache.isEmpty() );
        Clock.setTime( 3000 );
        assertNull( cache.get( 1 ) );
        assertTrue( cache.isEmpty() );

    }

    @Test
    public void testSize()  {
        assertEquals( 0, cache.size() );
        cache.put( 1, "apple" );
        assertEquals( 1, cache.size() );
        Clock.setTime( 3000 );
        assertEquals( 0, cache.size() );
    }

    @Test
    public void testPartialExpiry()  {
        //Add an apple, it will expire at 2000
        cache.put( 1, "apple" );
        Clock.setTime( 1500 );
        //Add an orange, it will expire at 2500
        cache.put( 2, "orange" );

        assertEquals( "apple", cache.get( 1 ) );
        assertEquals( "orange", cache.get( 2 ) );
        assertEquals( 2, cache.size() );

        //Set time to 2300 and check that only the apple has disappeared
        Clock.setTime( 2300 );

        assertNull( cache.get( 1 ) );
        assertEquals( "orange", cache.get( 2 ) );
        assertEquals( 1, cache.size() );
    }

    @Test
    public void testPutReturnValue() {
        cache.put( 1, "apple" );
        assertNotNull( cache.put( 1, "banana" ) );
        Clock.setTime( 3000 );
        assertNull( cache.put( 1, "mango" ) );
    }

    @Test
    public void testRemove()  {
        assertNull( cache.remove(1) );

        cache.put(1, "apple" );

        assertEquals( "apple", cache.remove(1) );

        assertNull( cache.get(1) );
        assertEquals( 0, cache.size() );

    }

    @Test
    public void testContainsKeyAndContainsValue() {
        assertFalse( cache.containsKey( 1 ) );
        assertFalse( cache.containsValue( "apple" ) );
        assertFalse( cache.containsKey( 2 ) );
        assertFalse( cache.containsValue( "orange" ) );

        cache.put( 1, "apple" );
        assertTrue( cache.containsKey( 1 ) );
        assertTrue( cache.containsValue( "apple" ) );
        assertFalse( cache.containsKey( 2 ) );
        assertFalse( cache.containsValue( "orange" ) );

        Clock.setTime( 3000 );
        assertFalse( cache.containsKey( 1 ) );
        assertFalse( cache.containsValue( "apple" ) );
        assertFalse( cache.containsKey( 2 ) );
        assertFalse( cache.containsValue( "orange" ) );
    }
}