package es.upm.dit.gsi.test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletUnitClient;

import edu.wpi.disco.Disco;
import edu.wpi.disco.Interaction;
import edu.wpi.disco.lang.Propose;
import es.upm.dit.gsi.WebAgentServlet;
import es.upm.dit.gsi.agent.AgentClass;
import es.upm.dit.gsi.agent.AgentClass.MyAgent;

public class TestServlet {


@SuppressWarnings("deprecation")
@Test
   public void testServlet() throws Exception , NullPointerException {
	
	   ServletRunner sr = new ServletRunner();
	   sr.registerServlet( "myServlet", WebAgentServlet.class.getName() );
	   ServletUnitClient sc= sr.newClient(); 
	   WebRequest request = new PostMethodWebRequest("http://test.meterware.com/myServlet");
	   request.setParameter("agent", "agent");
	   InvocationContext ic = sc.newInvocation(request);
	   WebResponse response= sc.getResponse(ic);
	   
	   
	   assertNotNull("no response received", response);
	   assertNull("a session already exsist", ic.getRequest().getSession(false));
	   assertNotNull("session was not created", ic.getRequest().getSession(true));
	   
	   //Creamos session
	   AgentClass agente1 = new AgentClass();
	   HttpSession session=(HttpSession)ic.getRequest().getSession(false);
	   session.setAttribute("misession", agente1);
	   agente1=(AgentClass)session.getAttribute("misession");
		
		//Parámetros necesarios para las pruebas
	   Interaction interaction = agente1.interaction();
	   Disco disco = interaction.getDisco();
	   String taskPath = session.getServletContext().getResource("/WebContent/WEB-INF/models/prueba.xml").getPath();
	   interaction.load(taskPath);
	   boolean 
       guess = interaction.getProperty("interaction@guess", true),
       retry = interaction.getProperty("interaction@retry", true);
	   interaction.occurred(true, Propose.Should.newInstance(disco, true, interaction.getTaskClass("WebAgent").newInstance()), null);
	   //interaction.getSystem().respond(interaction, false, guess, retry);
	   MyAgent agent = agente1.new MyAgent("agent");
	
		//pruebas agente
		assertTrue(interaction.getSystem().respond(interaction, false, guess, retry));
		
		assertEquals("How shall we using web agent?", agent.getAtributo());
		
	
		assertEquals("Let's using web agent by router.",agente1.menu(interaction).get(0));
		assertEquals("Let's using web agent by thermostat.",agente1.menu(interaction).get(1));
		assertEquals("Let's using web agent by smartTV.",agente1.menu(interaction).get(2));
		assertEquals("Let's not using web agent.",agente1.menu(interaction).get(3));
		
		interaction.occurred(true, Propose.Should.newInstance(disco, true, interaction.getTaskClass("SwitchRouter").newInstance()), null);
	
	
		assertTrue(interaction.getSystem().respond(interaction, false, guess, retry));
		assertEquals("How shall we set up the basic operation of a router?", agent.getAtributo());
		assertEquals("Let's set up the basic operation of a router connection router.",agente1.menu(interaction).get(0));
		assertEquals("Let's set up the basic operation of a router turning on router.",agente1.menu(interaction).get(1));
		assertEquals("Let's set up the basic operation of a router testing router.",agente1.menu(interaction).get(2));
		assertEquals("Let's not set up the basic operation of a router.",agente1.menu(interaction).get(3));
		assertEquals("Let's not using web agent.",agente1.menu(interaction).get(4));

		
		

		
		


}
}
