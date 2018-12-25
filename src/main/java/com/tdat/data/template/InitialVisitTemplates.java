package com.tdat.data.template;

public enum InitialVisitTemplates {
    CPB("Client Profile"), NARS("Needs Assessment and Referrals Service");

    private String templateName;

    InitialVisitTemplates(String s) {
        this.templateName = s;
    }

    public String getTemplateName() {
        return templateName;
    }

    public static boolean contains(String test) {

        for (InitialVisitTemplates c : InitialVisitTemplates.values()) {
            if (c.getTemplateName().equals(test)) {
                return true;
            }
        }

        return false;
    }
}
