package com.app.libraryproject.validate;

import com.app.libraryproject.dto.CreateLibraryCardRequest;
import com.app.libraryproject.dto.CreateMemberRequest;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class CreateLibraryCardRequestValidator {
    private final static String nationalIdRegex = "^[0-9]{11}$";

    public static boolean isValid(CreateLibraryCardRequest createLibraryCardRequest) {
        CreateMemberRequest member = createLibraryCardRequest.createMemberRequest();
        return
                !isBlank(member.name()) &&
                !isBlank(member.surname()) &&
                isNationalIdValid(member.nationalId()) &&
                member.birthday() != null;
    }

    private static boolean isNationalIdValid(String nationalId) {
        return nationalId != null && nationalId.matches(nationalIdRegex);
    }
}
