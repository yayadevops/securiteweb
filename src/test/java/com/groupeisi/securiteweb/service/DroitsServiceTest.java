package com.groupeisi.securiteweb.service;


import com.groupeisi.securiteweb.dao.DroitImpl;
import com.groupeisi.securiteweb.dto.DroitDto;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;


class DroitServiceTest {

    private DroitDtoImpl droitDtoImpl;
    private DroitImpl droitImpl = new DroitImpl();
    @BeforeAll
    static void initAll() {
        System.out.println("beforEach");
    }

    @BeforeEach
    void init() {
        droitDtoImpl = new DroitDtoImpl();
        droitImpl = new DroitImpl();
        System.out.println("beforEach");
    }

    @AfterEach
    void tearDown() {
        System.out.println("afterEach");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("afterAll");
    }

    @DisplayName("Creating a role and must return role id")
    @Test
    void add() {
        //Given
        DroitDto givenDroitDto = new DroitDto();
        givenDroitDto.setName("droit " + String.format("%d", (new Date()).getTime()));

        //When
        int result = droitDtoImpl.add(givenDroitDto);

        //Then
        DroitDto savedDroitDto = droitImpl.DroitEntityToDroitDto(droitImpl.getByNom(givenDroitDto.getName()));
        //assertEquals(result, savedDroitDto.getId());
        assertEquals(1, result);
        assertEquals(givenDroitDto.getName(), savedDroitDto.getName());
    }

    @Test
    @DisplayName("Updating a role and must update it and return role id")
    void update() {
        //Given
        DroitDto givenDroitDto = new DroitDto();
        givenDroitDto.setName("droit " + String.format("%d", (new Date()).getTime()));
        droitImpl.add(droitImpl.DroitDtoToDroitEntity(givenDroitDto));
        givenDroitDto = droitImpl.DroitEntityToDroitDto(droitImpl.getByNom(givenDroitDto.getName()));
        givenDroitDto.setName("updated droit " + String.format("%d", (new Date()).getTime()));

        //When
        int result = droitDtoImpl.update(givenDroitDto);

        //Then
        DroitDto updatedDroitDto = droitImpl.DroitEntityToDroitDto(droitImpl.getByNom(givenDroitDto.getName()));
        assertEquals(1, result);
        assertEquals(givenDroitDto.getName(), updatedDroitDto.getName());

    }

    @Test
    @DisplayName("Deleting a role and must delete it and return 1")
    void delete() {
        //Given
        DroitDto givenDroitDto = new DroitDto();
        givenDroitDto.setName("droit to delete after " + String.format("%d", (new Date()).getTime()));
        droitImpl.add(droitImpl.DroitDtoToDroitEntity(givenDroitDto));

        //When
        int result = droitDtoImpl.delete(droitImpl.getByNom(givenDroitDto.getName()).getId());

        //Then
        assertEquals(1, result);

    }


    @Test
    @DisplayName("Geting all roles and must return a List of DroitDto")
    void list() {
        //Given
        DroitDto givenDroitDto = new DroitDto();
        givenDroitDto.setName("droit to get " + String.format("%d", (new Date()).getTime()));
        droitImpl.add(droitImpl.DroitDtoToDroitEntity(givenDroitDto));
        givenDroitDto = droitImpl.DroitEntityToDroitDto(droitImpl.getByNom(givenDroitDto.getName()));
        //When
        List<DroitDto> results = droitDtoImpl.list();

        //Then
        assertInstanceOf(List.class, results);

    }


}