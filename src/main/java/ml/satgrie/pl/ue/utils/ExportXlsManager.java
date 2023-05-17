package ml.satgrie.pl.ue.utils;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;


@FacesConverter(value = "exportXlsManager")
public class ExportXlsManager implements Converter {

	 @Override
	    public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
	        DualListModel<XlsFile> model = (DualListModel<XlsFile>) ((PickList) comp).getValue();
	        for (XlsFile xls : model.getSource()) {
	            if (xls.getId().equals(value)) {
	                return xls;
	            }
	        }
	        for (XlsFile xls : model.getTarget()) {
	            if (xls.getId().equals(value)) {
	                return xls;
	            }
	        }
	        return null;
	    }

	    @Override
	    public String getAsString(FacesContext fc, UIComponent comp, Object value) {
	        return ((XlsFile) value).getId();
	    }

}
