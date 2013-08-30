package it.polito.ai.gas.business;


public enum UserType {
	ROLE_USER,
	ROLE_DELEGATE,
	ROLE_PRODUCER,
	ROLE_ADMIN;


    @Override
    public String toString() {
        switch(this) {
            case ROLE_USER: return "User";
            case ROLE_DELEGATE: return "Delegate";
            case ROLE_PRODUCER: return "Producer";
            case ROLE_ADMIN: return "Admin";
            default: throw new IllegalArgumentException();
        }
    }
}
