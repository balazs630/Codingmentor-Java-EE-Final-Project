package com.schonherz.project.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darvasr on 2016.09.13..
 */
public class EnumUtil {

    public static <E extends Enum<E>> List<String> getNames(Class<E> e) {
        List<String> names = new ArrayList<>();
        for (E enumConstant : e.getEnumConstants()) {
            names.add(enumConstant.name());
        }
        return names;
    }
}
