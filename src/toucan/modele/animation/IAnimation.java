package toucan.modele.animation;

import toucan.modele.LesCases;

public interface IAnimation {

    void executer(LesCases lesCases, int... lesIndices);

}
