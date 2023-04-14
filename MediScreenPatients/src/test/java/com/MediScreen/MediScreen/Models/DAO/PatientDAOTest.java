package com.MediScreen.MediScreen.Models.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.MediScreen.MediScreen.Models.DTO.PatientDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class PatientDAOTest {
    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    void testConstructor() {
        PatientDAO actualPatientDAO = new PatientDAO(
                new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", "4105551212"));
        assertEquals("Dob", actualPatientDAO.getBirthDate());
        assertEquals("42 Main St", actualPatientDAO.getPostalAddress());
        assertEquals("4105551212", actualPatientDAO.getPhoneNumber());
        assertEquals("Family", actualPatientDAO.getName());
        assertEquals("Sex", actualPatientDAO.getGender());
        assertEquals("Given", actualPatientDAO.getFirstName());
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: firstName is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setFirstName(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:52)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO(null, "Family", "Dob", "Sex", "42 Main St", "4105551212"));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: name is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setName(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:51)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO("Given", null, "Dob", "Sex", "42 Main St", "4105551212"));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: birthDate is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setBirthDate(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:53)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO("Given", "Family", null, "Sex", "42 Main St", "4105551212"));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: gender is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setGender(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:54)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO("Given", "Family", "Dob", null, "42 Main St", "4105551212"));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: postalAddress is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setPostalAddress(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:55)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO("Given", "Family", "Dob", "Sex", null, "4105551212"));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: phoneNumber is marked non-null but is null
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.setPhoneNumber(PatientDAO.java:18)
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:56)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(new PatientDTO("Given", "Family", "Dob", "Sex", "42 Main St", null));
    }

    /**
     * Method under test: {@link PatientDAO#PatientDAO(PatientDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testConstructor8() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.MediScreen.MediScreen.Models.DAO.PatientDAO.<init>(PatientDAO.java:51)
        //   In order to prevent <init>(PatientDTO)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   <init>(PatientDTO).
        //   See https://diff.blue/R013 to resolve this issue.

        new PatientDAO(null);
    }
}

