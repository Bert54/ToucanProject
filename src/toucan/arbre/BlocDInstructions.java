package toucan.arbre;

import java.util.ArrayList;

public class BlocDInstructions extends ArbreAbstrait {

    private ArrayList<Instruction> instructions;

    public BlocDInstructions() {
        this.instructions = new ArrayList<>();
    }

    /**
     * Ajoute une nouvelle instruction dans l'arbre
     * @param i La nouvelle instruction
     */
    public void ajouter(Instruction i) {
        this.instructions.add(i);
    }

    @Override
    public String getCodeDecore() {
        StringBuilder string = new StringBuilder("");
        for (Instruction i: this.instructions) {
            string.append(i.getCodeDecore());
        }
        return string.toString();
    }
}
