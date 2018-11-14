package toucan.arbre;

public abstract class Instruction extends ArbreAbstrait {

    protected boolean variableUtil;

    protected String removeTabinString(String str) {
        String temp = str.replaceAll("^tab\\[", "");
        temp = temp.replaceAll("]$", "");
        return temp;
    }

}
