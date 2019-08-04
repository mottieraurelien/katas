package csv.data;

import csv.domain.OdFullTrain;
import csv.domain.OdNoHst;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FilesRefStaticMapping {

    OD_NO_HST(OdNoHst.class, "/Users/aurel/GitProjects/katas/012_generics/src/main/resources/no_hst.csv"),
    OD_FULL_TRAIN(OdFullTrain.class, "/Users/aurel/GitProjects/katas/012_generics/src/main/resources/full_train.csv");

    private final Class type;
    private final String filename;

}
