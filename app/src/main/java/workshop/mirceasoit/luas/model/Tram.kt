package workshop.mirceasoit.luas.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(strict = false, name="tram")
data class Tram @JvmOverloads constructor (
    @field:Attribute(name = "dueMins", required = false) var dueMins: String = "",
    @field:Attribute(name = "destination", required = false) var destination: String = ""
)