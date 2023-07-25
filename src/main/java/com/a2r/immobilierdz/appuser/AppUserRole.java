package com.a2r.immobilierdz.appuser;

public enum   AppUserRole {
    USER, OWNER;

    public String getRoleName() {
        return this.toString().toLowerCase();
    }
}
