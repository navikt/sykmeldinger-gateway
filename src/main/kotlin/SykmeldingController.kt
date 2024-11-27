import kotlinx.html.*
import kotlinx.html.stream.createHTML

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Profile("local")
@RestController
class SykmeldingGatewayController(
) {
    private val logger = LoggerFactory.getLogger(SykmeldingGatewayController::class.java)


    @GetMapping("/")
    fun indexPage(): String {
        return createHTML().html {
            head {
                title("Sykmeldinger Gateway")

                script(src = "https://unpkg.com/htmx.org@1.9.10") {}
                script(src = "https://unpkg.com/htmx.org@1.9.10/dist/ext/remove-me.js") {}

                link(rel = "stylesheet", href = "https://unpkg.com/reset-css@5.0.2/reset.css")
                link(rel = "stylesheet", href = "/styles.css")
            }

            body {
                header {
                    h1 { + "Sykmeldinger Gateway" }
                }
            }
        }
    }

    @GetMapping("/styles.css")
    fun serveStyles(): String {
        val globalCss = """
            html { font-family: sans-serif; }
            body { margin: 0; }
            main { padding: 16px; display: grid; gap: 16px; grid-template-columns: 1fr 1fr; }
            header { border-bottom: 1px solid #ccc; height: 68px; display: flex; align-items: center; justify-content: space-between; padding-left: 22px; }
            h1 { font-size: 1.5em; font-weight: bold; margin-bottom: 8px; }
            button { padding: 8px 16px; border: none; background-color: #007bff; color: white; cursor: pointer; border-radius: 4px; }
            .success-feedback { background-color: #28a745; color: white; margin: 8px; padding: 8px; border-radius: 4px; max-width: 65ch; }
            .sykmelding-item {
                margin-bottom: 10px;  /* Legger til 10px mellom hvert element */
            }
        """.trimIndent()

        return globalCss
    }
}
