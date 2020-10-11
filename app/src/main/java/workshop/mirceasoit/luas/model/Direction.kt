package workshop.mirceasoit.luas.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(strict = false, name="direction")
data class Direction @JvmOverloads constructor (
    @field:Attribute(name = "name", required = false) var name: String = "",
    @field:ElementList(entry = "tram", required = false, inline = true) var trams: MutableList<Tram> = mutableListOf()
)


