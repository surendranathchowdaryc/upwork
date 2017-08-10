package com.upwork.screener;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.rules.*;

public class NetworkTest {
	Network network;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	
	@Test
	public void testNetworkWithLessThanOneNodeThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("A network should have atleast a node");
	    
		new Network(0);
		
	}
	
	@Test
	public void testNetworkWithOneNodeWillPass() throws Exception {
		new Network(1);
	}
	
	@Test
	public void testNetworkWithMoreThanOneNodeWilldPass() throws Exception {
		new Network(3);
	}
	
	@Test
	public void testConnectWithNodeNameZeroThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Nodes should be between 1 and 8");
	    
	    Network network = new Network(8);
	    network.connect(0, 2);

		
	}
	
	@Test
	public void testConnectWithNodeNameGreaterThanTotalNodesThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Nodes should be between 1 and 8");
	    
	    Network network = new Network(8);
	    network.connect(2, 9);

		
	}
	
	@Test
	public void testConnectWithSameNodesThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Nodes are same. Please enter two differnt nodes");
	    
	    Network network = new Network(8);
	    network.connect(2, 2);

		
	}
	
	@Test
	public void testConnectWithValidNodesAssertTrue() throws Exception {
		Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
	}
	
	@Test
	public void testQueryWithNodeNameZeroThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Nodes should be between 1 and 8");
	    
		Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
		
		network.query(0, 4);
	}
	
	@Test
	public void testQueryWithNodeNameGreaterThanTotalNodesThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("Nodes should be between 1 and 8");
	    
		Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
		
		network.query(2, 9);
	}
	
	@Test
	public void testQueryWithSameNodesThrowsException() throws Exception {
		expectedEx.expect(Exception.class);
	    expectedEx.expectMessage("A node is always connected to itself.");
	    
	    Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
		
		network.query(2, 2);
	}
	
	@Test
	public void testQueryWithNodesNotConnectedAssertFalse() throws Exception {	    
		Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
		
		assertFalse(network.query(5, 7));
		assertFalse(network.query(1, 3));
		assertFalse(network.query(6, 8));
	}
	
	@Test
	public void testQueryWithNodesConnectedAssertTrue() throws Exception {	    
		Network network = new Network(8);

		assertTrue(network.connect(1, 2));
		assertTrue(network.connect(1, 6));
		assertTrue(network.connect(2, 6));
		assertTrue(network.connect(2, 4));
		assertTrue(network.connect(5, 8));	
		
		assertTrue(network.query(1, 2));
		assertTrue(network.query(1, 6));
		assertTrue(network.query(1, 4));
		assertTrue(network.query(6, 4));
		assertTrue(network.query(5, 8));
	}
	

}
