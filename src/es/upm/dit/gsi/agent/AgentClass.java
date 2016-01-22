package es.upm.dit.gsi.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import edu.wpi.cetask.*;
import edu.wpi.disco.*;
import edu.wpi.disco.Agenda.Plugin;
import edu.wpi.disco.lang.*;
import edu.wpi.disco.Agent;


public class AgentClass {
	
	 public  void main (String[] args) { new AgentClass(); }
	 
	 
	 
private ArrayList<String> menu= new ArrayList<String>();
private  String agente = "";

List<Plugin.Item> plugin;


public Interaction interaction() { 
	   Interaction interaction = new Interaction(new MyAgent("agent"), new User("user"));
	  return interaction;
	   } 


public ArrayList<String> menu (Interaction interaction){
	  menu.clear();
	List<Plugin.Item> items =interaction.getExternal().generate(interaction);
   // print out formatted choices on system console
	int i=0; 
	 for (Plugin.Item item : items) {
		 //for (int i = 0; i < 1; i++) {
		 
  	String p = interaction.format(item, true, true);

  //	System.out.println("MENU ROUTER: "+p);
  	menu.add(i, p);
  	i++;
  //	 }
   }
	
	 return menu;
   
}


public String choose(int pos){
	String[] posiciones={"SwitchRouter","ConfigureThermostat","ConfigureSmartTVSamsung"};
	return posiciones[pos];
}


public Plugin.Item posicion ( int posicion, Interaction interaction){
	List<Plugin.Item> items =interaction.getExternal().generate(interaction);
	return items.get(posicion);
}

public List<Plugin.Item>  items (Interaction interaction){

	List<Plugin.Item> items =interaction.getExternal().generate(interaction);
	return items;
}

public class MyAgent extends Agent {
    
    public MyAgent (String name) { super(name); }
     
     public void say(Interaction interaction, Utterance utterance) {
        // here is where you would put natural language generation
        // and/or pass utterance string to TTS or GUI
        // for now we just call Disco's default formatting and print
        // out result on system console
   
   	agente= interaction.format(utterance);
       // System.out.println("AGENT: "+agente);
     
     }
     
     public String getAtributo (){
         return agente;
    }
     
    
     
  }
}
