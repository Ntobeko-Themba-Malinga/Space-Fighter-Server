package server.communication.response;


public class ResponseFactory {
    public static Response create(Responses type) {
        switch (type) {
            case BAD_REQUEST -> {
                return new BadRequestResponse();
            }
            case USER_REGISTER_SUCCESS -> {
                return new UserRegisterSuccessResponse();
            }
            case USER_REGISTER_FAIL -> {
                return new UserRegisterFailResponse();
            }
            case USER_LOGIN_SUCCESS -> {
                return new UserLoginSuccess();
            }
            case USER_LOGIN_FAIL -> {
                return new UserLoginFail();
            }
        }
        return null;
    }
}
