package com.franquicia.services;

import com.franquicia.models.*;
import com.franquicia.repository.MenuRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@Component
public class ActionService {

    String path1 = "/api/authenticate";
    String path2 = "/api/accion";
    String pathreporte = "/api/reporte/";
    @Value("${loggin.url}")
    private String url;
    @Value("${loggin.urlreporte}")
    private String urlreporte;
    @Value("${loggin.user}")
    private String user;
    @Value("${loggin.pass}")
    private String pass;
    @Value("${consulta.accion}")
    private String accion;
    @Value("${consulta.uuid}")
    private String uuid;
    private String tokenid;
    private String accion_service;
    private List<Menu> menu;
    private List<Menu> prod;
    private List<Menu> allmenudb;
    private Action info_sv;
    private Reporte reporte;
    @Autowired
    private MenuRepository repository;
    @Scheduled(cron = "${cron.expression}")
    public void checkactiontask() {

        System.out.println("\nCHEQUEANDO ACCION PROVENIENTE DEL SERVIDOR PRINCIPAL\n");
        if(this.tokenid == null) {

            WebClient webClient = WebClient.builder()
                    .baseUrl(url+path1)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            User body = new User();
            body.setUsername(user);
            body.setPassword(pass);

            Mono<Loggin> responseJson = webClient.post()
                    .body(Mono.just(body), User.class)
                    .retrieve().bodyToMono(Loggin.class);
            this.tokenid = responseJson.block().getId_token();
        }

        WebClient webClient = WebClient.builder()
                .baseUrl(url+path2)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        Consulta body = new Consulta();
        body.setAccion(accion);
        body.setFranquiciaID(uuid);

        Mono<Action> responseJson = webClient.post()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + this.tokenid + "\"")
                .body(Mono.just(body), Consulta.class)
                .retrieve().bodyToMono(Action.class);

        this.info_sv = responseJson.block();
        this.accion_service = info_sv.getAccion();
        this.menu = info_sv.getMenus();
        this.reporte = info_sv.getReporte();
        System.out.println("\nRespuesta Accion Servidor principal: " + 
        					"\n\tAccion: " + this.accion_service + 
        					"\n\tMenu: " + this.menu +
        					"\n\tReporte: " + this.reporte +
        					"\n");

        this.allmenudb = repository.findAll();

        if (this.menu != null){
            if(this.allmenudb.isEmpty() == true) {
                for (Menu menu : this.menu) {
                    System.out.println("\nGuardando menu!!");
                    repository.save(menu);
                }
        } else {for (Menu menu : this.menu) {
                this.prod = repository.findProdById(menu.getId());
                for (Menu prod : this.prod) {
                    if (menu.getActualizado().equals(prod.getActualizado())) {
                    }else{
                        System.out.println("\nMenu nuevo agregado");
                        repository.save(menu);
                    }
                }
              }
            }
        }
        
        if (this.reporte != null) {
        	System.out.println("\nEl servidor principal solicita a la franquicia un reporte");
        	System.out.println("\nEl servicio de franquicia informara al servicio de reporte\n");
        	WebClient webClient1 = WebClient.builder()
                    .baseUrl(urlreporte+pathreporte)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
        		System.out.println(urlreporte+pathreporte);
        		System.out.println(this.reporte);
        	Mono<Void> responseJson1 = webClient1.post()
                     .body(Mono.just(this.reporte), Reporte.class)
                     .retrieve().bodyToMono(Void.class);
        	
        	System.out.println("Respuesta Json: " + responseJson1.block());

        }
    }
}