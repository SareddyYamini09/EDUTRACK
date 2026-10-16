package services;

import data.DataManager;
import m4_flow.Dinic;
import m4_flow.FordFulkerson;
import model.Resource;
import utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service managing Campus Physical and Virtual Resources with integrated DSA algorithms:
 * - Ford-Fulkerson & Dinic Max Flow for classroom, lab, and workstation seat allocation
 */
public class ResourceService {
    private final DataManager dataManager;

    public ResourceService(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public List<Resource> getAllResources() {
        return dataManager.getResources();
    }

    public void displayAllResources() {
        List<Resource> resources = getAllResources();
        DisplayUtils.printSubHeader("Campus Academic Resources (" + resources.size() + ")");
        String[] headers = {"Resource ID", "Name", "Type", "Location", "Capacity", "Status"};
        List<String[]> rows = new ArrayList<>();
        for (Resource r : resources) {
            rows.add(new String[]{
                    r.getResourceId(), r.getResourceName(), r.getResourceType(),
                    r.getLocation(), String.valueOf(r.getCapacity()),
                    r.isAvailable() ? "AVAILABLE" : "OCCUPIED"
            });
        }
        DisplayUtils.printTable(headers, rows);
    }

    public void allocateClassroomsNetworkFlow() {
        DisplayUtils.printCard("Classroom Capacity Allocation via Ford-Fulkerson & Dinic", "M4 / CO4",
                "Dinic: O(V^2 * E), FF: O(E * max_flow)", "O(V + E)", "Campus Hall & Lab Capacity Scheduling");

        List<Resource> resources = getAllResources();
        int totalAvailableCapacity = 0;
        for (Resource r : resources) {
            if (r.isAvailable()) {
                totalAvailableCapacity += r.getCapacity();
            }
        }

        System.out.println("Total Registered Campus Resources:    " + resources.size());
        System.out.println("Current Available Seat/Slot Capacity: " + totalAvailableCapacity);

        System.out.println("\nExecuting Dinic's Algorithm for Multi-Tier Student Cohort Routing...");
        Dinic.demonstrate();

        System.out.println("\nExecuting Ford-Fulkerson Step-by-Step Residual Augmentation...");
        FordFulkerson.demonstrate();
    }

    public void addResource(Resource resource) {
        for (Resource r : dataManager.getResources()) {
            if (r.getResourceId().equalsIgnoreCase(resource.getResourceId())) {
                DisplayUtils.printWarning("Resource ID " + resource.getResourceId() + " already exists!");
                return;
            }
        }
        dataManager.getResources().add(resource);
        dataManager.saveResources();
        DisplayUtils.printSuccess("Resource " + resource.getResourceName() + " added successfully.");
    }
}
