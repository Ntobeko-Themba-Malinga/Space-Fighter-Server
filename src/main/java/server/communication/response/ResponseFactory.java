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
            case TOKEN_NOT_FOUND -> {
                return new TokenNotFoundResponse();
            }
            case INVALID_USER -> {
                return new InvalidUserRequestResponse();
            }
            case GAME -> {
                return new GameRequestResponse();
            }
            case INVALID_GAME_REQUEST -> {
                return new InvalidGameRequestResponse();
            }
            case INVALID_GAME_COMMAND -> {
                return new InvalidGameCommandResponse();
            }
            case USER_LOGOUT_SUCCESS -> {
                return new UserLogoutSuccess();
            }
        }
        return null;
    }
}
