package es.upm.dit.gsi;


import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import atg.taglib.json.util.JSONException;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.wpi.cetask.Decomposition;
import edu.wpi.cetask.DecompositionClass;
import edu.wpi.cetask.Plan;
import edu.wpi.cetask.Task;
import edu.wpi.disco.Agenda;
import edu.wpi.disco.Agenda.Plugin.Item;
import edu.wpi.disco.Agent;
import edu.wpi.disco.Disco;
import edu.wpi.disco.Interaction;
import edu.wpi.disco.User;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.Propose;
import edu.wpi.disco.lang.Propose.ShouldNot;
import edu.wpi.disco.lang.TTSay;
import edu.wpi.disco.lang.Utterance;
import edu.wpi.disco.plugin.ProposeShouldNotPlugin;
import es.upm.dit.gsi.agent.AgentClass;
import es.upm.dit.gsi.agent.AgentClass.MyAgent;
import edu.wpi.disco.game.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.SingleThreadModel;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.soap.Node;

import net.sf.json.JSONObject;
/**
 * Servlet implementation class WebAgentServlet
 */

@SuppressWarnings("deprecation")
public class WebAgentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected String path = null;
	private String misession= null;
	AgentClass agente1;
	User user= new User("user");
	JSONObject json;
	MyAgent agent;
	List<Plugin.Item> items;
	List<Plugin.Item> plugin;
	NWayInteraction interact;
	Interaction interaction; 
	

	   /**
     * @see HttpServlet#HttpServlet()
     */
    public WebAgentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig servletConfig) throws ServletException{
        this.path = servletConfig.getInitParameter("path");

     }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings({ "deprecation", "static-access" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= (HttpSession)request.getSession();
		//Creamos sesion y guardamos el agente1 en una variable
		if ( (AgentClass)session.getAttribute(misession) == null){
			agente1 = new AgentClass();
			session.setAttribute("misession", agente1);
		}
		//Creamos agente de AgenteClass
		agente1= (AgentClass)session.getAttribute("misession");
		
		agent = agente1.new MyAgent("agent");
		interaction= new Interaction(agent, new User("user"));
		Disco disco = interaction.getDisco();
		   boolean 
	       guess = interaction.getProperty("interaction@guess", true),
	       retry = interaction.getProperty("interaction@retry", true);
		//Cogemos el taskModel
		String taskPath = session.getServletContext().getResource(path).getPath();
		//Lee el modelo de tareas.
		interaction.load(taskPath);
		

		//si no está iniciada la conversación

	if(request.getParameter("q")==null){
		//System.out.println(Interaction.currentThread());
		//System.out.println(interaction.isAlive());
		//inicia la conversación por el nodo raiz
		interaction.occurred(true, Propose.Should.newInstance(disco, true, interaction.getTaskClass("WebAgent").newInstance()), null);
	
		interaction.getSystem().respond(interaction, false, guess, retry);

		//generamos el json con la respuesta del agente y las posibles respuestas del usuario
		
		json=json(agent.getAtributo(), agente1.menu(interaction));
		//guardamos los items generados en esta iteraccion
		System.out.println(agent.getAtributo());
		System.out.println(interaction);
		setPlugin(interaction.getExternal().generate(interaction));
		

		//enviamos a la vista los datos para mostras al usuario
		
		session.setAttribute("json", json);
		session.setAttribute("interaction", interaction);
		session.setAttribute("agent", agent);
		
		request.getRequestDispatcher("AgentView.jsp").forward(request, response);
		

		//si ya esta iniciada la conversación
	}else if(request.getParameter("q")!=null){
	
		// inicio conversacion
		//interaction.occurred(true, Propose.Should.newInstance(disco, true, interaction.getTaskClass("WebAgent").newInstance()), null);
		//interaction.getSystem().respond(interaction, false, interaction.getProperty("interaction@guess", true), interaction.getProperty("interaction@retry", true));
		//Guardamos las iteracciones de la conversaciion
		//setPlugin(interaction.getExternal().generate(interaction));
		//cogemos los items generados
		interaction= (Interaction)session.getAttribute("interaction");
		agent=(AgentClass.MyAgent)session.getAttribute("agent");
		
		
		List<Plugin.Item> item=getPlugin();
		// guardamos el item que el usuario seleccione
	
		Plugin.Item mostrar= item.get(Integer.parseInt(request.getParameter("q")));
		//respuesta del agente a dicha iteracción del usuario
		System.out.println(mostrar);
		
		interaction.occurred(true, mostrar.task, mostrar.contributes);
		interaction.doTurn(false);
		//respuesta
		interaction.getSystem().respond(interaction, false, guess, retry);
		//interaction.getSystem().respond(interaction, false, interaction.getProperty("interaction@guess", true), interaction.getProperty("interaction@retry", true));
		//Agent.synchronizedRespond(interaction, false, interaction.getProperty("interaction@guess", true), interaction.getProperty("interaction@retry", true));)
		//respuestas
		
		json=json(agent.getAtributo(), agente1.menu(interaction));
		setPlugin(interaction.getExternal().generate(interaction));
		setinteraction(interaction);
		session.setAttribute("json", json);
		request.getRequestDispatcher("AgentView.jsp").forward(request, response);
		session.setAttribute("interaction", interaction);
		
		
	
		// mandamos las respuestas a la vista
		
		
		
		}
	if(agent.getAtributo().equals("Ok.")){
		session.invalidate();
		System.out.println("Lorena");
	}
	
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Creamos un json para pasar los datos a la vista
	 * @param response la respuesta del agente
	 * @param menu las posibles respuestas del usuario
	 * @return
	 */
	public JSONObject json( String response, ArrayList<String> menu){
	
		JSONObject json= new JSONObject();
		JSONObject dialog= new JSONObject();			
		json.put("response", response);
		json.put("menu", menu);
		dialog.put("dialog", json);
	System.out.println(dialog);
	return json;
	
	}
		
	public void setPlugin(List<Plugin.Item> plugins){
		plugin=plugins;
		
	}
	public void setinteraction (Interaction interaction2){
		interaction=interaction2;
	}
	public Interaction getInteraction (){
		return interaction;
	}
	public List<Plugin.Item> getPlugin(){
		return plugin;
	}
}
