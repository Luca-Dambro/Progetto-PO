package Model;

import Controller.ApiController;
import Controller.CheckOffer;
import Controller.FileController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;

/**
 * @author Chiara
 */
public class PickedJobs extends JobBoard {

    private String fileName = "PickedJobs.txt";
    private FileController fileController = new FileController(fileName);

    public PickedJobs() throws IOException {
        //fileController = new FileController(fileName);
        if (this.jobs == null) {
            this.jobs.addAll(fileController.readJobsFromFile());
        }
    }

    //aggiunto da ben
    public boolean setJobsFromFile() throws IOException {
        //fileController = new FileController(fileName);

        if(fileController.readJobsFromFile() == null){
            return false;
        }
        else{
            jobs.addAll(fileController.readJobsFromFile());
            return true;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //bdihcbeihbcihebrihcbeibci
    //aggiunge un lavoro all'hash e salva
    public void add(Job job) throws IOException {

        for (Job j : jobs)
            fileController.save(this.jobs);
        if (!jobs.contains(job)) {
            jobs.add(job);
            fileController.save(this.jobs);
        }

        /*
        HashSet<Job> jobs = new HashSet<>();
        jobs.addAll(fileController.readJobsFromFile());
        this.jobs = jobs;
        //jobs.clear();

        for (Job j : jobs) {
            //fileController.save(this.jobs);
            if (!this.jobs.contains(job)) {
                this.jobs.add(job);
                fileController.save(this.jobs);
            }/*
            else{

            }
        }*/

    }

    //elimina un lavoro
    public void deleteJob(String id) throws IOException {
        boolean found = false;
        for (Job j : jobs)
            if (j.getId().equals(id)) {
                jobs.remove(j);
                fileController.save(jobs);
                return;
            }
    }

    public void addAll(HashSet<Job> jobs){
        for(Job j : jobs){
            try {
                this.add(j);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /*
    public void Update() throws Exception {
        //scorre jobs e per ogni lavoro se non è più presente lo cancella
        for (Job j : jobs) {
            if (!CheckOffer.verify(j)) {
                this.deleteJob(j.getId());
                fileController.save(jobs);
            }
        }
    }*/

    public Boolean verifyJobsPresent(){

        try {
            if(fileController.readJobsFromFile() == null)
                return false;
            else{
                return true;
            }
        } catch (IOException e) {
            return false;
        }
    }

    public void deleteAll(){
        try {
            fileController.deleteAll();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /*public Job searchForJob(String id)
    {
        for(Job j : this.jobs)
        {
            if(j.getId().equals(id)) return j;
        }
        return null;
    }*/
}