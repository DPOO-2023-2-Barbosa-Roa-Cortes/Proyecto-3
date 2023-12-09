package edu.dpoo.accounts;

public class AdminAccount {
    public static class Global extends UserAccount {
        public Global(String fullName, String username, int password) {
            super(fullName, username, password);
        }
        public static Global parse(String text) {
            String[] format = text.split(";");
            return new Global(format[0], format[1], Integer.parseInt(format[2]));
        }
    }

    public static class Local extends UserAccount {
        protected String branch;
        public Local(String fullName, String username, int password, String branch) {
            super(fullName, username, password);
            this.branch = branch;
        }
        public static Local parse(String text) {
            String[] format = text.split(";");
            return new Local(format[0], format[1], Integer.parseInt(format[2]), format[3]);
        }
    }
}
