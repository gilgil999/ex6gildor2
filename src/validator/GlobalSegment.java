package validator;
import parser.TypeOneException;

import java.util.*;


public class GlobalSegment extends CodeSegment {


    public GlobalSegment() {
        super();

    }

    @Override
    public boolean isValid(ScopeObj scopeObj) throws TypeOneException {
        rearrangeFunctions(scopeObj);
        if(hasTwoFucntionsWithTheSameName(scopeObj))
            throw new TypeOneException();
        //no need for deepcopying the scopeobj
        for (Checkable line : this.children){
            if(!line.isValid(scopeObj))
                throw new TypeOneException();

        }
        return true;
    }

    private boolean hasTwoFucntionsWithTheSameName(ScopeObj scopeObj) {
        ArrayList<FunctionObj> funcs = scopeObj.getFuncs();

        int len=funcs.size();
        String[] names=new String[len];

        for(int i=0;i<len;i++){
            names[i]=funcs.get(i).getName();
        }
        for (int i=0;i<len-1;i++){
            for (int j=i+1;j<len;j++){
                if(names[i].equals(names[j]))
                    return true;
            }
        }
        return false;
    }

    /**
     * this function goes over all of the children of
     * @param scopeObj
     */
    private void rearrangeFunctions(ScopeObj scopeObj) {
        ArrayList<FunctionSegment> myfuncs = new ArrayList<FunctionSegment>();
        int len = this.children.size();
        ArrayList<Checkable> toremove=new ArrayList<>();
        for(int i=0;i<len;i++){
            Checkable line=this.children.get(i);
            if(line instanceof FunctionSegment){
                //todo function can not have the same name
                scopeObj.addFundtion(((FunctionSegment)line).getThisfunc());
                myfuncs.add((FunctionSegment)line);
//                this.children.remove(line);
                toremove.add(line);
            }
        }
        this.children.removeAll(toremove);
        this.children.addAll(myfuncs);


    }
}
