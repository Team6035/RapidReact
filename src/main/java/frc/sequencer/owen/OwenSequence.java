package frc.sequencer.owen;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.lang.model.util.SimpleTypeVisitor8;

import frc.robot.subsystems.Intake;
import frc.sequencer.IntakeStep;
import frc.sequencer.Sequence;
import frc.sequencer.ShootStep;

public class OwenSequence
{
    // This is a simple example of how to provide a number of
    // sequences to the SmartDashboard to be able to be chosen.
    // Simply create a new method for each sequence, the method
    // must return the sequence object, and they will be added
    // to SmartDashboard.
    public static synchronized List<Sequence> getSequences()
    {
        if (theSequences == null)
        {
            theSequences = new LinkedList<Sequence>();
            theSequences.add(createDefault());
            // theSequences.add(createSquare());
            theSequences.add(create1a());
            theSequences.add(create2a());
            theSequences.add(create3a());
            theSequences.add(create4a());
        }
        return Collections.unmodifiableList(theSequences);
    }

    private static Sequence createDefault()
    {
        ShootStep shoot = new ShootStep();

        OwenTimer transition1 =  new OwenTimer();
        transition1.setDelay(5);

        OwenDrive step2 =new OwenDrive() ;
        step2.setDist(-1.34);
        step2.setAngle(0);
        OwenDrive transition2 = step2 ;


        transition1.setNextTrans(transition2);
        transition1.setNextSteps(step2);
        Sequence seq = new Sequence("0 - Universal shoot & Move", 0);
        seq.setInitialSteps(shoot);
        seq.setInitialTransitions(transition1);
        return seq;
    }

    private static Sequence createSquare()
     {
        OwenTimer transition1 = new OwenTimer();
        transition1.setDelay(4);
        OwenDrive steps1 = new OwenDrive();
        steps1.setDist(3);
        OwenDrive transitions1 = steps1;

        OwenTimer transition2 = new OwenTimer();
        transition2.setDelay(4);
        OwenTurn steps2 = new OwenTurn();
        steps2.setAngle(90);
        OwenTurn transitions2 = steps2;

        OwenTimer transition3 = new OwenTimer();
        transition3.setDelay(4);
        OwenDrive steps3 = new OwenDrive();
        steps3.setAngle(90);
        steps3.setDist(3);
        OwenDrive transitions3 = steps3;

        OwenTimer transition4 = new OwenTimer();
        transition4.setDelay(4);
        OwenTurn steps4 = new OwenTurn();
        steps4.setAngle(180);
        OwenTurn transitions4 = steps4;

        OwenTimer transition5 = new OwenTimer();
        transition5.setDelay(4);
        OwenDrive steps5 = new OwenDrive();
        steps5.setAngle(180);
        steps5.setDist(3);
        OwenDrive transitions5 = steps5;

        OwenTimer transition6 = new OwenTimer();
        transition6.setDelay(4);
        OwenTurn steps6 = new OwenTurn();
        steps6.setAngle(270);
        OwenTurn transitions6 = steps6;
    
        OwenTimer transition7 = new OwenTimer();
        transition7.setDelay(4);
        OwenDrive steps7 = new OwenDrive();
        steps7.setAngle(270);
        steps7.setDist(3);
        OwenDrive transitions7 = steps7;
   
        OwenTimer transition8 = new OwenTimer();
        transition8.setDelay(4);
        OwenTurn steps8 = new OwenTurn();
        steps8.setAngle(0);
        OwenTurn transitions8 = steps8;

    transitions1.setNextTrans(transitions2);
    transitions1.setNextSteps(steps2);
    transitions2.setNextTrans(transitions3);
    transitions2.setNextSteps(steps3);
    transitions3.setNextTrans(transitions4);
    transitions3.setNextSteps(steps4);
    transitions4.setNextTrans(transitions5);
    transitions4.setNextSteps(steps5);
    transitions5.setNextTrans(transitions6);
    transitions5.setNextSteps(steps6);
    transitions6.setNextTrans(transitions7);
    transitions6.setNextSteps(steps7);
    transitions7.setNextTrans(transitions8);
    transitions7.setNextSteps(steps8);
    transitions8.setNextTrans(transitions1);
    transitions8.setNextSteps(steps1);
    
        Sequence seq = new Sequence("Owen Square", 0);
        seq.setInitialSteps(steps1);
        seq.setInitialTransitions(steps1);
        return seq;
    }

    private static Sequence createTriangle()
    {
        //  OwenTimer transition1 = new OwenTimer();
        // transition1.setDelay(4);
        OwenDrive stept1 = new OwenDrive();
        stept1.setDist(4);
        OwenDrive transitiont1 = stept1;

        //  OwenTimer transition2 = new OwenTimer();
        // transition2.setDelay(4);  
        OwenTurn stept2 = new OwenTurn();
        stept2.setAngle(90);
        OwenTurn transitiont2 = stept2;

        OwenDrive stept3 = new OwenDrive();
        stept3.setAngle(90);
        stept3.setDist(4);
        OwenDrive transitiont3 = stept3;

        // OwenTimer transition4 = new OwenTimer();
        // transition4.setDelay(4.0);
        OwenTurn stept4 = new OwenTurn();
        stept4.setAngle(-135);
        OwenTurn transitiont4 = stept4;

        // OwenTimer transition5 = new OwenTimer();
        // transition5.setDelay(4.0);
        OwenDrive stept5 = new OwenDrive();
        stept5.setAngle(-135);
        stept5.setDist(Math.sqrt(32));
        OwenDrive transitiont5 = stept5;

        // OwenTimer transition6 = new OwenTimer();
        // transition6.setDelay(4.0);
        OwenTurn stept6 = new OwenTurn();
        stept6.setAngle(0);
        OwenTurn transitiont6 = stept6;

        transitiont1.setNextTrans(transitiont2);
        transitiont1.setNextSteps(stept2);
        transitiont2.setNextTrans(transitiont3);
        transitiont2.setNextSteps(stept3);
        transitiont3.setNextTrans(transitiont4);
        transitiont3.setNextSteps(stept4);
        transitiont4.setNextTrans(transitiont5);
        transitiont4.setNextSteps(stept5);
        transitiont5.setNextTrans(transitiont6);
        transitiont5.setNextSteps(stept6);
        transitiont6.setNextTrans(transitiont1);
        transitiont6.setNextSteps(stept1);

        Sequence seq = new Sequence("Owen Triangle", 0);
        seq.setInitialSteps(stept1);
        seq.setInitialTransitions(transitiont1);
        return seq;
    }

    private static Sequence create1a()
    {
        IntakeStep inStep = new IntakeStep();
        ShootStep shoot = new ShootStep();

        OwenTurn step1 = new OwenTurn();
        step1.setAngle(-80);
        OwenTurn transition1 = step1;

        OwenTimer transition2 =  new OwenTimer();
        transition2.setDelay(2);

        OwenTurn step3 = new OwenTurn();
        step3.setAngle(100);
        OwenTurn transition3 = step3;

        // Forward to pick up ball, end up against field wall.
        OwenDrive step4 =new OwenDrive() ;
        step4.setDist(1.34);
        step4.setAngle(100);
        OwenDrive transition4 = step4 ;

        OwenTimer transition5 = new OwenTimer();
        transition5.setDelay(1);
        
        // Move back from wall to allow robot to turn.
        OwenDrive step6 = new OwenDrive() ;
        step6.setDist(-0.20) ;
        step6.setAngle(100);
        OwenDrive transition6 = step6 ;

        // Turn to face hub for shooting.
        OwenTurn step7 = new OwenTurn() ;
        step7.setAngle(-80);
        OwenTurn transition7 = step7 ;
    
        // Shoot two balls into hub.
        // TODO replace timer with shooter code.
        // TODO add shooter spinup to earlier steps
        OwenTimer transition8 = new OwenTimer() ;
        transition8.setDelay(2);

        // Turn to drive to teminal
        OwenTurn step9 = new OwenTurn() ;
        step9.setAngle(-150);
        OwenTurn transition9 = step9 ;

        OwenDrive step10 = new OwenDrive() ;
        step10.setDist(2.3);
        step10.setAngle(-150);
        OwenDrive transition10 = step10 ;

        // go to tbe terminal to intake the ball
        // TODO put the intake code in step8

        OwenTurn step11 = new OwenTurn() ;
        step11.setAngle(-50) ;
        step11.setDeadband(5);
        OwenTurn transition11 = step11 ;

        OwenTimer step12 = new OwenTimer() ;
        step12.setDelay(5);
        OwenTimer transition12 = step12 ;

        transition1.setNextTrans(transition2);
        transition1.setNextSteps(shoot);
        transition2.setNextTrans(transition3);
        transition2.setNextSteps(step3, inStep);
        transition3.setNextTrans(transition4);
        transition3.setNextSteps(step4, inStep);
        transition4.setNextTrans(transition5);
        transition4.setNextSteps(inStep);
        transition5.setNextTrans(transition6);
        transition5.setNextSteps(step6);
        transition6.setNextTrans(transition7);
        transition6.setNextSteps(step7);
        transition7.setNextTrans(transition8);
        transition7.setNextSteps(shoot);
        transition8.setNextTrans(transition9);
        transition8.setNextSteps(step9);
        transition9.setNextTrans(transition10);
        transition9.setNextSteps(step10, inStep);
        transition10.setNextTrans(transition11);
        transition10.setNextSteps(step11);
        transition11.setNextTrans(transition12);
        transition11.setNextSteps(shoot);
        Sequence seq = new Sequence("1A - 3 ball", 1);
        seq.setInitialSteps(step1, inStep);
        seq.setInitialTransitions(step1);
        return seq;
    }


    private static Sequence create4a()
    {
        owenShooter shoot = new owenShooter();
        OwenIndexer inStep = new OwenIndexer();

        // drives forwars to intake ball then shoot
        // TODO put intake code into step1

        OwenTurn step1 =new OwenTurn() ;
        step1.setAngle(35);
        step1.setDeadband(5);
        OwenTurn transition1 = step1 ;

        OwenTimer step3 = new OwenTimer() ;
        step3.setDelay(2);
        OwenTimer transition3 = step3 ;

        // put limelight and shoot code here 
        OwenTurn step4 = new OwenTurn() ;
        step4.setAngle(-135);
        OwenTurn transition4 =step4 ;

        // steps5-10 drive to terminal to intake

        OwenDrive step5 = new OwenDrive() ;
        step5.setAngle(-135);
        step5.setDist(1.3);
        OwenDrive transition5 = step5 ;

        OwenTurn step6 = new OwenTurn() ;
        step6.setAngle(15);
        step6.setDeadband(5);
        step6.setGain(0.5);
        OwenTurn transition6 = step6 ;
        
        OwenTimer step7 =new OwenTimer() ;
        step7.setDelay(5);
        OwenTimer transition7 = step7 ;

    //     OwenDrive step8 = new OwenDrive() ;
    //     step8.setDist(4.9);
    //     step8.setAngle(90);
    //     OwenDrive transition8 = step8 ;

    //     OwenTurn step9 = new OwenTurn() ;
    //     step9.setAngle(-45);
    //     OwenTurn transition9 = step9;

    //     OwenDrive step10 = new OwenDrive() ;
    //     step10.setAngle(-45);
    //    step10.setDist(-1);
    //     OwenDrive transition10 = step10 ;
        
    //     OwenCurve step11 = new OwenCurve() ;
    //     step11.setRadius(0.5);
    //     step11.setAngle(10);
    //     OwenCurve transition11 = step11 ;
        
        // put limeLIght code here

        // OwenDrive step12 = new OwenDrive() ;
        // step12.setDist(0.5);
        // step12.setAngle(-45);
        // OwenDrive transition12 = step12 ;
        
        // OwenDrive step13 = new OwenDrive() ;
        // step13.setDist(0.5);
        // step13.setAngle(-35);
        // OwenDrive transition13 = step13 ;

        // OwenTurn step14 = new OwenTurn() ;
        // step14.setAngle(-190);
        // OwenTurn transition14 =step14 ;

        

        transition1.setNextTrans(transition3);
        transition1.setNextSteps(shoot);
        transition3.setNextTrans(transition4);
        transition3.setNextSteps(step4);
        transition4.setNextTrans(transition5);
        transition4.setNextSteps(step5, inStep);
        transition5.setNextTrans(transition6);
        transition5.setNextSteps(step6);
        transition6.setNextTrans(transition7);
        transition6.setNextSteps(shoot);
        // transition7.setNextTrans(transition8);
        // transition7.setNextSteps(step8);
        // transition8.setNextTrans(transition9);
        // transition8.setNextSteps(step9);
        // transition9.setNextTrans(transition10);
        // transition9.setNextSteps(step10);
        // transition10.setNextTrans(transition11);
        // transition10.setNextSteps(step11);
       
       
       
        // transition11.setNextTrans(transition12);
        // transition11.setNextSteps(step12);
        // transition12.setNextTrans(transition13);
        // transition12.setNextSteps(step13);
        // transition13.setNextTrans(transition14);
        // transition13.setNextSteps(step14);



        Sequence seq = new Sequence("4 - 2 ball", 4);
        seq.setInitialSteps(step1,shoot);
       seq.setInitialTransitions(step1);
        return seq;
    }

private static Sequence create3a()
    {
        IntakeStep inStep = new IntakeStep();
        ShootStep shoot = new ShootStep();
        
        OwenTurn step1 = new OwenTurn();
        step1.setAngle(-15);
        OwenTurn transition1 = step1 ;

        OwenTimer step2 = new OwenTimer();
        step2.setDelay(2);
        OwenTimer transition2 = step2 ;

        OwenTurn step3 = new OwenTurn() ;
        step3.setAngle(180);
        OwenTurn transition3 = step3 ;

        OwenDrive step4 = new OwenDrive();
        step4.setAngle(180);
        step4.setDist(4);
        OwenDrive transition4 = step4 ;

        transition1.setNextTrans(transition2);
        transition1.setNextSteps(shoot);
        transition2.setNextTrans(transition3);
        transition2.setNextSteps(step3);
         transition3.setNextTrans(transition4);
        transition3.setNextSteps(step4);
        // transition4.setNextTrans(transition5);

        Sequence seq = new Sequence("3 - 1 ball and run", 3);
        seq.setInitialSteps(step1);
        seq.setInitialTransitions(step1);
        return seq;
    }

    private static Sequence create2a() 
    {
        ShootStep shoot = new ShootStep();
        IntakeStep inStep = new IntakeStep();

        OwenTimer transition1 =  new OwenTimer();
        transition1.setDelay(2);

        OwenTurn step2 = new OwenTurn();
        step2.setAngle(170);
        OwenTurn transition2 = step2;

        OwenDrive step3 =new OwenDrive() ;
        step3.setDist(1.5);
        step3.setAngle(170);
        OwenDrive transition3 = step3 ;

        OwenTurn step4 = new OwenTurn();
        step4.setAngle(-
        30);
        step4.setDeadband(5);
        OwenTurn transition4 = step4;

        OwenTimer transition5 = new OwenTimer();
        transition5.setDelay(5);

        transition1.setNextTrans(transition2);
        transition1.setNextSteps(step2);
        transition2.setNextTrans(transition3);
        transition2.setNextSteps(step3);
        transition3.setNextTrans(transition4);
        transition3.setNextSteps(step4);
        transition4.setNextTrans(transition5);
        transition4.setNextSteps(shoot);
        Sequence seq = new Sequence("2 - 2 ball", 2);
        seq.setInitialSteps(shoot);
        seq.setInitialTransitions(transition1);
        return seq;
    }
   

    static LinkedList<Sequence> theSequences = null;
}
