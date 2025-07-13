package acal.com.core.comons

import com.fasterxml.uuid.Generators

class Id {

    companion object {
        fun random(): String =
            Generators.timeBasedGenerator()
                .generate().toString()
    }

}

