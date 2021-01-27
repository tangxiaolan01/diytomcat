package buider;

public interface Imenu {
    Imenu appendCeiling(Matter matter);

    Imenu appendTile(Matter matter);

    Imenu appendCoat(Matter matter);

    Imenu appendFloor(Matter matter);


    String detail();
}
