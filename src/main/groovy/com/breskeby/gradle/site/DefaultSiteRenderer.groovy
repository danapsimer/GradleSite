package com.breskeby.gradle.site

/**
 * Created by IntelliJ IDEA.
 * @author: Rene
 */

import groovy.xml.MarkupBuilder
import java.text.DateFormat
import org.gradle.util.GradleVersion

class DefaultSiteRenderer {

    MarkupBuilder builder;

    final SiteModel model;

    public DefaultSiteRenderer(SiteModel model) {
        this.model = model
    }

    void render(File outputFile, String contentString = "") {
        OutputStream outputStream = new FileOutputStream(outputFile);

        try {
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "utf-8"));
            writer.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
            builder = new MarkupBuilder(new IndentPrinter(writer, ""));
            builder.html {
                head {
                    meta('http-equiv': "Content-Type", content: "text/html; charset=UTF-8")
                    title(model.title)
                    link(rel: 'stylesheet', href: 'style.css', type: 'text/css')
                    script(src: 'report.js', type: 'text/javascript', " ")
                }
                body {
                    div(id: 'content') {
                        div(id:'header'){
                            img(src:"${model.headerTitle}")
                        }
                        div(id:'main'){
                            div(id: "successRate", class: "infoBox success") {
                                div(id: "navcolumn") {
                                    model.menu.each { section, items ->
                                        h3(section)
                                        ul {
                                            items.each { item, link ->
                                                li(class: "none") {
                                                    a(href: link, item)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            div(id:'mainContent'){
                                renderContent()
                            }
                        }

						div(id: 'footer') {
	                        p {
	                            mkp.yield('Generated by ')
	                            a(href: 'http://www.gradle.org', "Gradle ${GradleVersion.current().version}")
	                            mkp.yield(" at ${DateFormat.getDateTimeInstance().format(new Date())}")
	                        }
	                    }
                    }
                }
            }
            writer.flush();
        } finally {
            outputStream.close();
        }

    }

    protected void renderContent(){

    }
}




