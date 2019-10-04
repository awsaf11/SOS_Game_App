package eecs1022.sos.models;

import android.widget.Button;

/**
 * Created by user on 2/21/18.
 */
public class GridEntry
{
    //Selection can be these values: 'N' 'S' 'O'
    String selection;
    boolean hasBeenChecked;
    boolean hasBeenApartOfSequence;
    int gridButtonId;

    public GridEntry(int gridButtonId){
        this.hasBeenChecked = false;
        this.hasBeenApartOfSequence = false;
        this.selection = "N";
        this.gridButtonId = gridButtonId;
    }

    public void setSelection(String selection){
        this.selection = selection;
    }

    public String getSelection(){
        return this.selection;
    }

    public void setGridButton(int gridButtonId)
    {
        this.gridButtonId = gridButtonId;
    }

    public int getGridButtonId()
    {
        return gridButtonId;
    }

    public void setHasBeenChecked(boolean hasBeenChecked)
    {
        this.hasBeenChecked = hasBeenChecked;
    }

    public boolean getHasBeenChecked()
    {
        return this.hasBeenChecked;
    }

    public void sethasBeenApartOfSequence(boolean hasBeenApartOfSequence)
    {
        this.hasBeenApartOfSequence = hasBeenApartOfSequence;
    }

    public boolean gethasBeenApartOfSequence()
    {
        return this.hasBeenApartOfSequence;
    }


}
