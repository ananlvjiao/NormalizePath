package com.normalizepath;


public class PathNormalizer {
	
	public String normalize(String path)
	{
		if(path == null || path.length() == 0) return "";
		//split string by slash, keep the last empty string
        String[] strs = path.split("/", -1);
        int eIndex = strs.length-1;
        StringBuilder strBuilder = new StringBuilder();
        //store back steps for .. case
        int back = 0;
        for(int i=eIndex; i>=0 ; i--){
            if(strs[i].length()>0){
                if(strs[i].equals(".")){
                    continue;
                }
                else if(strs[i].equals("..")){
                    back++;
                }
                else{
                    if(back!=0){back--;}
                    else{
                    	if(i!=eIndex){
                        	strBuilder.insert(0,'/');
                        }
                        strBuilder.insert(0, strs[i]);
                    }
                }
            }
            else if(i!=eIndex){
            	//if it is not the last string and it is empty, then add /
            	 strBuilder.insert(0,'/');
            }
        }
        return strBuilder.toString();
	}

}
