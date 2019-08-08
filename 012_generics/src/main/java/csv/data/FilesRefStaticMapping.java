package csv.data;

import csv.domain.OdFullTrain;
import csv.domain.OdNoHst;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FilesRefStaticMapping {

    OD_NO_HST(OdNoHst.class, "/Workspace/katas/012_generics/src/main/resources/no_hst.csv"),
    OD_FULL_TRAIN(OdFullTrain.class, "/Workspace/katas/012_generics/src/main/resources/full_train.csv");

    private final Class beanClass;
    private final String filename;

    public static FilesRefStaticMapping from(Class beanClass) {
        return Stream.of(values())
                .filter(enumMapping -> enumMapping.getBeanClass() == beanClass)
                .findFirst()
                .orElseThrow(() -> new EnumConstantNotPresentException(FilesRefStaticMapping.class, beanClass.getName()));
    }

}