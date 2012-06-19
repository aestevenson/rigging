

package com.castaway.rigging

class AttributeValueController {
    
	static navigation = [
		[group:'tabs', action:'list', title: 'List Attribute Values', order: 90]
	]

	def index = { redirect(action:list,params:params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [delete:'POST', save:'POST', update:'POST']

    def list = {
        params.max = Math.min( params.max ? params.max.toInteger() : 10,  100)
        [ attributeValueInstanceList: AttributeValue.list( params ), attributeValueInstanceTotal: AttributeValue.count() ]
    }

    def show = {
        def attributeValueInstance = AttributeValue.get( params.id )

        if(!attributeValueInstance) {
            flash.message = "AttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
        else { return [ attributeValueInstance : attributeValueInstance ] }
    }

    def delete = {
        def attributeValueInstance = AttributeValue.get( params.id )
        if(attributeValueInstance) {
            try {
                attributeValueInstance.delete()
                flash.message = "AttributeValue ${params.id} deleted"
                redirect(action:list)
            }
            catch(org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "AttributeValue ${params.id} could not be deleted"
                redirect(action:show,id:params.id)
            }
        }
        else {
            flash.message = "AttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
    }

    def edit = {
        def attributeValueInstance = AttributeValue.get( params.id )

        if(!attributeValueInstance) {
            flash.message = "AttributeValue not found with id ${params.id}"
            redirect(action:list)
        }
        else {
            return [ attributeValueInstance : attributeValueInstance ]
        }
    }

    def update = {
        def attributeValueInstance = AttributeValue.get( params.id )
        if(attributeValueInstance) {
            if(params.version) {
                def version = params.version.toLong()
                if(attributeValueInstance.version > version) {
                    
                    attributeValueInstance.errors.rejectValue("version", "attributeValue.optimistic.locking.failure", "Another user has updated this AttributeValue while you were editing.")
                    render(view:'edit',model:[attributeValueInstance:attributeValueInstance])
                    return
                }
            }
            attributeValueInstance.properties = params
            if(!attributeValueInstance.hasErrors() && attributeValueInstance.save()) {
                flash.message = "AttributeValue ${params.id} updated"
                redirect(action:show,id:attributeValueInstance.id)
            }
            else {
                render(view:'edit',model:[attributeValueInstance:attributeValueInstance])
            }
        }
        else {
            flash.message = "AttributeValue not found with id ${params.id}"
            redirect(action:edit,id:params.id)
        }
    }

    def create = {
        def attributeValueInstance = new AttributeValue()
        attributeValueInstance.properties = params
        return ['attributeValueInstance':attributeValueInstance]
    }
	
	def createAnother = {
		redirect(action:create)
	}

    def save = {
        def attributeValueInstance = new AttributeValue(params)
        if(!attributeValueInstance.hasErrors() && attributeValueInstance.save()) {
            flash.message = "AttributeValue ${attributeValueInstance.id} created"
            redirect(action:show,id:attributeValueInstance.id)
        }
        else {
            render(view:'create',model:[attributeValueInstance:attributeValueInstance])
        }
    }
}
