package friends.api

class UrlMappings {
    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/friends"(resources:'friends')
        "/distance"(resources:'distance')
        "/distance/$id?/$id2"(resource: 'distance', action: 'show')

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
