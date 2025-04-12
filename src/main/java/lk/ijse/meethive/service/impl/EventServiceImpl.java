/*
package lk.ijse.meethive.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventFacilityDTO;
import lk.ijse.meethive.dto.EventFacilityDetailsDTO;
import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.EventFacility;
import lk.ijse.meethive.entity.EventFacilityDetails;
import lk.ijse.meethive.entity.EventType;
import lk.ijse.meethive.repo.EventFacilityDetailsRepo;
import lk.ijse.meethive.repo.EventFacilityRepo;
import lk.ijse.meethive.repo.EventRepo;
import lk.ijse.meethive.repo.EventTypeRepo;
import lk.ijse.meethive.service.EventFacilityDetailsService;
import lk.ijse.meethive.service.EventFacilityService;
import lk.ijse.meethive.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepository;

    @Autowired
    private EventFacilityRepo eventFacilityRepository;

    @Autowired
    private EventFacilityDetailsService eventFacilityDetailsService;

    @Autowired
    private EventTypeRepo eventTypeRepo;

    @Autowired
    private EventFacilityService eventFacilityService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EventFacilityDetailsRepo eventFacilityDetailsRepo;


    @Transactional
    public void saveEvent(EventDTO eventDTO) {
        Event event = new Event();

        EventType eventType = eventTypeRepo.findById(eventDTO.getEventType_Id())
                .orElseThrow(() -> new RuntimeException("Event Type not found: " + eventDTO.getEventType_Id()));//id eka ganne nathi prshnayak tinawa balanna

        event.setEventTitle(eventDTO.getEventTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDateTime(eventDTO.getStartDateTime());
        event.setEndDateTime(eventDTO.getEndDateTime());
        event.setEventType(eventType);

        Event saveEvent = eventRepository.save(event);

        List<EventFacilityDetailsDTO> eventFacilityDetailsDTOS = eventDTO.getEventFacilities();

        for (EventFacilityDetailsDTO eventFacilityDetailsDTO : eventFacilityDetailsDTOS){
            EventFacility eventFacility = eventFacilityRepository.findById(String.valueOf(eventFacilityDetailsDTO.getEventFacilityID()))
                    .orElseThrow(() -> new RuntimeException("Event Facility not found: " + eventFacilityDetailsDTO.getEventFacilityID()));

            EventFacilityDetails eventFacilityDetails = new EventFacilityDetails();
            eventFacilityDetails.setEvent(saveEvent);
            eventFacilityDetails.setEventFacility(eventFacility);
            eventFacilityDetails.setQuantity(eventFacilityDetailsDTO.getQty());

            eventFacilityDetailsRepo.save(eventFacilityDetails);

            eventFacilityRepository.updateQty(eventFacility.getQty(), String.valueOf(eventFacilityDetails.getQuantity()));
        }
    }
}
*/

package lk.ijse.meethive.service.impl;

import jakarta.transaction.Transactional;
import lk.ijse.meethive.dto.EventDTO;
import lk.ijse.meethive.dto.EventFacilityDetailsDTO;
import lk.ijse.meethive.entity.Event;
import lk.ijse.meethive.entity.EventFacility;
import lk.ijse.meethive.entity.EventFacilityDetails;
import lk.ijse.meethive.entity.EventType;
import lk.ijse.meethive.repo.EventFacilityDetailsRepo;
import lk.ijse.meethive.repo.EventFacilityRepo;
import lk.ijse.meethive.repo.EventRepo;
import lk.ijse.meethive.repo.EventTypeRepo;
import lk.ijse.meethive.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepo eventRepository;

    @Autowired
    private EventFacilityRepo eventFacilityRepository;

    @Autowired
    private EventTypeRepo eventTypeRepo;

    @Autowired
    private EventFacilityDetailsRepo eventFacilityDetailsRepo;

    /*@Transactional
    public void saveEvent(EventDTO eventDTO) {
        Event event = new Event();

        EventType eventType = eventTypeRepo.findById(eventDTO.getEventType_Id())
                .orElseThrow(() -> new RuntimeException("Event Type not found: " + eventDTO.getEventType_Id()));

        event.setEventTitle(eventDTO.getEventTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDateTime(eventDTO.getStartDateTime());
        event.setEndDateTime(eventDTO.getEndDateTime());
        event.setEventType(eventType);

        Event savedEvent = eventRepository.save(event);

        List<EventFacilityDetailsDTO> eventFacilityDetailsDTOS = eventDTO.getEventFacilities();

        Set<String> processedFacilityIds = new HashSet<>();

        for (EventFacilityDetailsDTO eventFacilityDetailsDTO : eventFacilityDetailsDTOS) {
            String facilityId = String.valueOf(eventFacilityDetailsDTO.getEventFacilityID());

            if (processedFacilityIds.contains(facilityId)) {
                continue;
            }

            processedFacilityIds.add(facilityId);

            EventFacility eventFacility = eventFacilityRepository.findById(facilityId)
                    .orElseThrow(() -> new RuntimeException("Event Facility not found: " + facilityId));
            System.out.println(eventFacility);

            EventFacilityDetails eventFacilityDetails = new EventFacilityDetails();
            eventFacilityDetails.setEvent(savedEvent);
            eventFacilityDetails.setEventFacility(eventFacility);
            eventFacilityDetails.setQuantity(eventFacilityDetailsDTO.getQty());

            eventFacilityDetailsRepo.save(eventFacilityDetails);

            int updatedRows = eventFacilityRepository.updateQty(eventFacilityDetailsDTO.getQty(), facilityId);

            if (updatedRows > 0) {
                System.out.println("Updated qty for: " + facilityId);
            } else {
                System.out.println("Failed to update qty for: " + facilityId);
            }
        }
    }*/
    @Transactional
    public void saveEvent(EventDTO eventDTO) {
        Event event = new Event();

        EventType eventType = eventTypeRepo.findById(eventDTO.getEventType_Id())
                .orElseThrow(() -> new RuntimeException("Event Type not found: " + eventDTO.getEventType_Id()));

        event.setEventTitle(eventDTO.getEventTitle());
        event.setDescription(eventDTO.getDescription());
        event.setStartDateTime(eventDTO.getStartDateTime());
        event.setEndDateTime(eventDTO.getEndDateTime());
        event.setEventType(eventType);

        Event savedEvent = eventRepository.save(event);

        List<EventFacilityDetailsDTO> eventFacilityDetailsDTOS = eventDTO.getEventFacilities();

        Set<String> processedFacilityIds = new HashSet<>();

        for (EventFacilityDetailsDTO eventFacilityDetailsDTO : eventFacilityDetailsDTOS) {
            // Get the facility ID and quantity
            String facilityId = String.valueOf(eventFacilityDetailsDTO.getEventFacilityID());
            int requestedQty = eventFacilityDetailsDTO.getQty();

            // Skip if already processed or if quantity is zero
            if (processedFacilityIds.contains(facilityId) || requestedQty <= 0) {
                continue;
            }

            processedFacilityIds.add(facilityId);

            // Debug logging
            System.out.println("Processing facility ID: " + facilityId + " with quantity: " + requestedQty);

            // Find the facility
            EventFacility eventFacility = eventFacilityRepository.findById(facilityId)
                    .orElseThrow(() -> new RuntimeException("Event Facility not found: " + facilityId));

            // Debug logging - print current facility quantity
            System.out.println("Current facility quantity: " + eventFacility.getQty());

            // Check if there's enough quantity available
            if (eventFacility.getQty() < requestedQty) {
                throw new RuntimeException("Insufficient quantity available for facility ID: " + facilityId);
            }

            // Create and save facility details
            EventFacilityDetails eventFacilityDetails = new EventFacilityDetails();
            eventFacilityDetails.setEvent(savedEvent);
            eventFacilityDetails.setEventFacility(eventFacility);
            eventFacilityDetails.setQuantity(requestedQty);  // Make sure this value is correct

            // Debug the event facility details before saving
            System.out.println("Saving event facility details with quantity: " + eventFacilityDetails.getQuantity());

            eventFacilityDetailsRepo.save(eventFacilityDetails);

            // Update facility quantity directly
            int newQty = eventFacility.getQty() - requestedQty;
            eventFacility.setQty(newQty);
            eventFacilityRepository.save(eventFacility);

            System.out.println("Updated facility " + facilityId + " quantity to: " + newQty);
        }
    }

}
