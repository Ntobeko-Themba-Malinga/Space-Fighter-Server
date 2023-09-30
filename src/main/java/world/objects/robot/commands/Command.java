package world.objects.robot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;
import world.IWorld;
import world.objects.robot.Robot;

import java.util.ArrayList;
import java.util.List;

public abstract class Command {
    private JsonNode arguments;
    private String result;
    private String message;
    private JSONObject hitObject = new JSONObject();
    private List<JSONObject> objects = new ArrayList<>();
    private JSONObject status = new JSONObject();

    /**
     * Creates instances of commands that don't have arguments.
     */
    public Command() {
        this.arguments = null;
    }

    /**
     * Creates instances of commands that have arguments.
     * @param arguments
     */
    public Command(JsonNode arguments) {
        this.arguments = arguments;
    }

    /**
     * Commands instructions.
     * @param world The world object to use in the execution of the command.
     * @param username The robot object to use in the execution of the command.
     * @return JSONObject containing the results of the command execution.
     */
    public abstract String execute(IWorld world, String username);

    public String buildResponse() {
        JSONObject response = new JSONObject();
        response.put("result", result);
        response.put("hit_object", hitObject);
        response.put("objects", objects);
        response.put("status", status);
        response.put("message", message);
        return response.toString();
    }

    public JsonNode getArguments() {
        return arguments;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setHitObject(JSONObject hitObject) {
        this.hitObject = hitObject;
    }

    public void setObjects(List<JSONObject> objects) {
        this.objects = objects;
    }

    public void setStatus(JSONObject status) {
        this.status = status;
    }
}
