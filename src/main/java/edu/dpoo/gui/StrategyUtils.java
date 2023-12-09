package edu.dpoo.gui;

public enum StrategyUtils {
    L_LOGIN {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, L_SIGNUP {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, S_LOGIN {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, S_SIGNUP {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, CH_LOGOUT {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, CH_CONTINUE {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, CP_CANCEL {
        @Override String execute(CustomerView view) {
            return null;
        }
    }, CP_PAY {
        @Override String execute(CustomerView view) {
            return null;
        }
    };

    abstract String execute(CustomerView view);
}
