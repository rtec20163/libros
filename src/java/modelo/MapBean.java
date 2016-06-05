/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;  
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;   
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;  
import javax.faces.event.ActionEvent;  

import org.primefaces.model.map.DefaultMapModel;  
import org.primefaces.model.map.LatLng;  
import org.primefaces.model.map.MapModel;  
import org.primefaces.model.map.Marker;
@SuppressWarnings("serial")
@ManagedBean
public class MapBean implements Serializable {  

    private MapModel emptyModel;  

    private String title;  

    private double lat;  

    private double lng;
    
    private Marker marker;

    private List<LatLng> coordenadas;

    public List<LatLng> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<LatLng> coordenadas) {
        this.coordenadas = coordenadas;
    }

    public MapBean() {  
        emptyModel = new DefaultMapModel();  
    }  

    public MapModel getEmptyModel() {  
        return emptyModel;  
    }  

    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  

    public String getTitle() {  
        return title;  
    }  

    public void setTitle(String title) {  
        this.title = title;  
    }  

    public double getLat() {  
        return lat;  
    }  

    public void setLat(double lat) {  
        this.lat = lat;  
    }  

    public double getLng() {  
        return lng;  
    }  

    public void setLng(double lng) {  
        this.lng = lng;  
    }

    public void populateTable(List<LatLng> lista){
        lista.add(new LatLng(getLat(), getLng()));
    }

    public void addMarker(ActionEvent actionEvent) {
        Marker marker = new Marker(new LatLng(lat, lng), title);
        this.marker = marker;
        coordenadas = new ArrayList<LatLng>();
        emptyModel.addOverlay(marker);  

        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));

        populateTable(coordenadas);

    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

}