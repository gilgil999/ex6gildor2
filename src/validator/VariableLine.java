package validator;

import parser.MainParser;

import java.util.ArrayList;

import static parser.MainParser.isCompatible;

public class VariableLine extends Singleline {


    private boolean isFinal;
    private ArrayList<VarOperation> operations;

    public VariableLine(boolean isFinal, ArrayList<VarOperation> operations) {
        this.isFinal = isFinal;
        this.operations = operations;
    }

    @Override
    public boolean isValid(ScopeObj scopeObj) {


        for (VarOperation operation : operations) {
            if (operation.getDestination().getName() == null) {
                /////System.out.println("no name to dest");
                return false;//the variable does not have a name specified

            }

            //check if the destination variable is in scope or are being defined
            if (operation.getDestination().getType() == MainParser.varType.UNKNOWN) {

                VarObj dest = scopeObj.getVar(operation.getDestination().getName());

                //type is not specified, meaning that it is an assigment without deceleration
                if (operation.getSource().getName() == null && operation.getSource().getType() == MainParser.varType.UNKNOWN) {
                    //a legal statement should either have a source name or a source destination
                    /////System.out.println("source not have type and name");
                    return false;
                }
                //dest needs to be in scope
                if (dest == null)
                //it is either not in scope or doesnt have a name
                {
                    /////System.out.println("dest is not in scope or doesnt have a name");
                    return false;
                }
                //we now need to check if dest is final, if so it cant be assigned
                if (dest.isFinal()) {
                    /////System.out.println("dest is final");
                    return false;
                }
                //we now need to check if the type of source is comptible with the type of dest
                //source can be either a variable in the scope or an immidiate with a specified type

                if (operation.getSource().getType() == MainParser.varType.UNKNOWN) {
                    VarObj source;

                    //it is a variable, it has to be in the scope
                    source = scopeObj.getVar(operation.getSource().getName());
                    if (source == null) {
                        /////System.out.println("source not in scope");
                        return false;
                    }
                    //checking if the source variable is assigned
                    if (!source.isAssigned()) {
                        /////System.out.println("source is not assigned");
                        return false;
                    }
                    //checking compatibility
                    if (!isCompatible(dest.getType(), source.getType())) {
                        /////System.out.println("typs are not compatible1");
                        return false;
                    }
                    //they are compatible, updating VarObj status to match as assigned variable
                    dest.setAssigned(true);
                } else {
                    //the type of source is defined, meaning that it is a constant
                    if (!isCompatible(dest.getType(), operation.getSource().getType())) {
                        /////System.out.println("typs are not compatible2");
                        return false;

                    }
                }
                dest.setAssigned(true);

            } else {//the type of dest is known, meaning that it is a deceleration
                //checking if a variable in that name already exists in the scope
                VarObj dest = scopeObj.getVar(operation.getDestination().getName());

                if (dest == null) {

                    //it is not in scope
                    //it needs to be defined

                    dest = new VarObj(operation.getDestination().getName(), operation.getDestination().getType());
                    dest.setFinal(isFinal);
                    dest.setOverridable(false);
                    scopeObj.update(dest);

                } else {
                    //is is in the scope, we need to check if it is overridable
                    if (!dest.isOverridable()) {
                        /////System.out.println("trying to override an unoverridable");
                        return false;
                    }
                    //it is overridable, we now need to check if source is defined and we can
                    dest = new VarObj(operation.getDestination().getName(), operation.getDestination().getType());
                    dest.setFinal(isFinal);
                    scopeObj.update(dest);
                }
                if (operation.getSource().getName() == null&&operation.getSource().getType() == MainParser.varType.UNKNOWN) {//checking if source is nonexistent
                    if (isFinal) {
                        /////System.out.println("final without initializtion");
                        return false;
                    } else {
                        /////System.out.println("no initialization, not final");
                        continue;
                    }
                }

                //the source exists
                if (operation.getSource().getType() == MainParser.varType.UNKNOWN) {//when assigning values to the
                    //operation variables, their type has to be UNKNOWN
                    VarObj source;

                    //it is a variable, it has to be in the scope


                    //source is existant therefore it has to have a name
                    source = scopeObj.getVar(operation.getSource().getName());

                    //check if it is in the scope
                    if (source == null) {
                        /////System.out.println("source is null");
                        return false;
                    }
                    //checking if the source variable is assigned
                    if (!source.isAssigned()) {
                        /////System.out.println("source is not assigned");
                        return false;
                    }
                    //checking compatibility
                    if (!isCompatible(dest.getType(), source.getType())) {
                        /////System.out.println("types are not compatible3");
                        return false;
                    }
                    //they are compatible, updating VarObj status to match as assigned variable
                    dest.setAssigned(true);
                } else {
                    //the type of source is defined, meaning that it is a constant
                    if (!isCompatible(dest.getType(), operation.getSource().getType())) {
                        /////System.out.println("types are not compatible4");
                        return false;
                    }
                }
                dest.setAssigned(true);

            }


        }
        return true;
    }



}
