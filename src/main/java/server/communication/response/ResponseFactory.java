package server.communication.response;

public class ResponseFactory {

    public static Response create(String type) {
        switch (type.toLowerCase()) {
            case "bad_request" -> {
                return new BadRequestResponse();
            }
        }
        return null;
    }
}
