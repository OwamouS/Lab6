package Control;


import Control.cmdLists.CommandList;
import Control.cmdLists.StdCommandList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

//client

/**
 * Class that takes a command from Controller and interpret it
 */
public class CommandInterpreter implements Interpreter {
    /**
     * Command List that Interpreter uses, changeable
     */
    private CommandList cmdList =  new StdCommandList();

    /**
     * This method takes the command, separate it on command and arguments
     * and then interpret command according to the current Command List
     * @param args Command
     * @throws IOException If an I/O error occurs
     */
    @Override
    public void handle(String[] args) throws IOException {
            if (cmdList.getCommands().containsKey(args[0])) {
                CommandController.getCommandHistory().addCommand(cmdList.getCommands().get(args[0]).toString());
                try {
                    ArrayList<String> arguments;
                    if (args.length > 1) {
                        arguments = new ArrayList<>(Arrays.asList(args));
                        arguments.remove(0);
                    } else arguments = null;
                    if (arguments != null) for (int i=0;i<= arguments.size()-1;i++) {
                        if (";".equals(arguments.get(i))){
                            arguments.remove(i);
                            arguments.add(i,"");
                        }
                    }
                    cmdList.getCommands().get(args[0]).execute(arguments == null ? null : arguments.toArray(new String[0]));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (NullPointerException e){
                    System.out.println("Wrong arguments...");
                }
            }
            else {
                System.out.println("There is no such command. Enter help to see a list of available commands ..");
            }
        }

    /**
     * This method change the Command List that concrete Interpreter uses
     * @param list New Command List
     */
    public void changeCommandList(CommandList list){
        cmdList = list;
    }
}