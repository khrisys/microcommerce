package com.mcommerce.zuulserver.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Classe permettant de faire des filtres sur les requetes envoyées depuis le client vers les differents MS. Les filtres
 * peuvent aussi etre créés pour etre actifs depuis les Ms vers le client.
 *
 * @Component Indique qu'une classe annotée est une "composante".
 *  - Ces classes sont considérées comme candidates à l'auto-détection
 *  - lors de l'utilisation d'une configuration basée sur des annotations et d'une analyse de classpath.
 */
@Component
public class LogFilter extends ZuulFilter {
    
    private Logger vLogger = LoggerFactory.getLogger(this.getClass());
    
    //Choix du moment où je veux que le filtre s'applique :avec "pre" ou "post". Lorsque la requete va arrive, si "pre" est
    // choisi, alors la requete sera filtrée avant d'etre relayée vers le MS. Sio n choisi "post", le filtre sera executé dans
    // le sens contraire, donc du MS vers le client.
    @Override
    public String filterType() {
        return "pre";
    }
    
    // permet de definir l'ordre d'execution du filtre. S'il y a par x plusieurs dizaines de filtres, on pourra definir un
    // ordre d'executon des filtres
    @Override
    public int filterOrder() {
        return 0;
    }
    
    // permet dire si ce filtre doit s'executer ou pas. Est ce que la requete doit etre filtrée ou est ce qu'on laisse la
    // requete telle quelle?
    @Override
    public boolean shouldFilter() {
        return true;
    }
    
    // traitement sur la requete. ici, on affiche les log de la requete qui passe
    @Override
    public Object run() throws ZuulException {
    
        HttpServletRequest vHttpServlet = RequestContext.getCurrentContext().getRequest();
        
        vLogger.info(" *************** Requete interceptée dans zuul : " + vHttpServlet.getRequestURL());
        
        return null;
    }
}
