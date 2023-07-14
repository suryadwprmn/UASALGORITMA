package connect;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;


public class ResponJSON {
    public static void selectionShort(ResponModel[] data){
        int s = data.length;
        for (int i = 0 ; i < s-1 ;i++){
            int index = i;
            for (int j = i +1 ; j< s; j++){
                if(data[j].getRating() < data[index].getRating()){
                    index = j;
                }
            }
            ResponModel temp = data[index];
            data[index] = data[i];
            data[i] = temp;
        }
    }

    public static void main(String[] args) throws IOException{

        ConnectURI connect= new ConnectURI();
        URL addres = connect.buildURL("https://dummyjson.com/products");
        String response = connect.getResponseFromHttpurl(addres);
        assert response != null;
        JSONArray responJSON = new JSONArray(response);
        ArrayList<ResponModel> responseModel = new ArrayList<>();
        for (int i = 0 ; i< responJSON.length(); i++){
            ResponModel resModel = new ResponModel();
            JSONObject myJSONObject = responJSON.getJSONObject(i);
            resModel.setId(myJSONObject.getInt("id"));
            resModel.setTitle(myJSONObject.getString("title"));
            resModel.setDescription(myJSONObject.getString("description"));
            resModel.setPrice(myJSONObject.getInt("price"));
            resModel.setDiscountPercentage(myJSONObject.getDouble("discountPercentage"));
            resModel.setRating(myJSONObject.getDouble("rating"));
            resModel.setStock(myJSONObject.getInt("stock"));
            resModel.setBrand(myJSONObject.getString("brand"));
            resModel.setCategory(myJSONObject.getString("category"));
            resModel.setThumbnail(myJSONObject.getString("thumbnail"));
            responseModel.add(resModel);
        }
        ResponModel[] products;
        products =new ResponModel[responseModel.size()];
        for (int i = 0; i < responseModel.size();i++){
                products[i] = responseModel.get(i);
        }

        //foreach
        selectionShort(products);
        for (ResponModel responModel : products){
            System.out.println("Id " + responModel.getId());
            System.out.println("title " + responModel.getTitle());
            System.out.println("description "+ responModel.getDescription());
            System.out.println("price " + responModel.getPrice());
            System.out.println("discount " + responModel.getDiscountPercentage());
            System.out.println("rating " + responModel.getRating());
            System.out.println("stock " + responModel.getStock());
            System.out.println("brand " + responModel.getBrand());
            System.out.println("category " + responModel.getCategory());
            System.out.println("thumbnail " + responModel.getThumbnail());
        }
    }
}
